import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ICategoryElement } from 'app/shared/model/category-element.model';
import { getEntities as getCategoryElements } from 'app/entities/category-element/category-element.reducer';
import { ITransferMethodManagement } from 'app/shared/model/transfer-method-management.model';
import { getEntity, updateEntity, createEntity, reset } from './transfer-method-management.reducer';

export const TransferMethodManagementUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const categoryElements = useAppSelector(state => state.categoryElement.entities);
  const transferMethodManagementEntity = useAppSelector(state => state.transferMethodManagement.entity);
  const loading = useAppSelector(state => state.transferMethodManagement.loading);
  const updating = useAppSelector(state => state.transferMethodManagement.updating);
  const updateSuccess = useAppSelector(state => state.transferMethodManagement.updateSuccess);

  const handleClose = () => {
    navigate('/transfer-method-management');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getCategoryElements({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  // eslint-disable-next-line complexity
  const saveEntity = values => {
    if (values.id !== undefined && typeof values.id !== 'number') {
      values.id = Number(values.id);
    }

    const entity = {
      ...transferMethodManagementEntity,
      ...values,
      type: categoryElements.find(it => it.id.toString() === values.type?.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...transferMethodManagementEntity,
          type: transferMethodManagementEntity?.type?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.transferMethodManagement.home.createOrEditLabel" data-cy="TransferMethodManagementCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.transferMethodManagement.home.createOrEditLabel">
              Create or edit a TransferMethodManagement
            </Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="transfer-method-management-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.transferMethodManagement.code')}
                id="transfer-method-management-code"
                name="code"
                data-cy="code"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.transferMethodManagement.desc')}
                id="transfer-method-management-desc"
                name="desc"
                data-cy="desc"
                type="text"
              />
              <ValidatedField
                id="transfer-method-management-type"
                name="type"
                data-cy="type"
                label={translate('tfbitaApp.transferMethodManagement.type')}
                type="select"
              >
                <option value="" key="0" />
                {categoryElements
                  ? categoryElements.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/transfer-method-management" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default TransferMethodManagementUpdate;
