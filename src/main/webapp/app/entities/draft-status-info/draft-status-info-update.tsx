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
import { IDraftStatusInfo } from 'app/shared/model/draft-status-info.model';
import { getEntity, updateEntity, createEntity, reset } from './draft-status-info.reducer';

export const DraftStatusInfoUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const categoryElements = useAppSelector(state => state.categoryElement.entities);
  const draftStatusInfoEntity = useAppSelector(state => state.draftStatusInfo.entity);
  const loading = useAppSelector(state => state.draftStatusInfo.loading);
  const updating = useAppSelector(state => state.draftStatusInfo.updating);
  const updateSuccess = useAppSelector(state => state.draftStatusInfo.updateSuccess);

  const handleClose = () => {
    navigate('/draft-status-info');
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
    if (values.remainAmount !== undefined && typeof values.remainAmount !== 'number') {
      values.remainAmount = Number(values.remainAmount);
    }

    const entity = {
      ...draftStatusInfoEntity,
      ...values,
      status: categoryElements.find(it => it.id.toString() === values.status?.toString()),
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
          ...draftStatusInfoEntity,
          status: draftStatusInfoEntity?.status?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.draftStatusInfo.home.createOrEditLabel" data-cy="DraftStatusInfoCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.draftStatusInfo.home.createOrEditLabel">Create or edit a DraftStatusInfo</Translate>
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
                  id="draft-status-info-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.draftStatusInfo.approved')}
                id="draft-status-info-approved"
                name="approved"
                data-cy="approved"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftStatusInfo.draftRequestAccepted')}
                id="draft-status-info-draftRequestAccepted"
                name="draftRequestAccepted"
                data-cy="draftRequestAccepted"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftStatusInfo.insuranceCostPaid')}
                id="draft-status-info-insuranceCostPaid"
                name="insuranceCostPaid"
                data-cy="insuranceCostPaid"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftStatusInfo.issued')}
                id="draft-status-info-issued"
                name="issued"
                data-cy="issued"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftStatusInfo.otherCostsPaid')}
                id="draft-status-info-otherCostsPaid"
                name="otherCostsPaid"
                data-cy="otherCostsPaid"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftStatusInfo.postSwiftCostPaied')}
                id="draft-status-info-postSwiftCostPaied"
                name="postSwiftCostPaied"
                data-cy="postSwiftCostPaied"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftStatusInfo.rejectDescription')}
                id="draft-status-info-rejectDescription"
                name="rejectDescription"
                data-cy="rejectDescription"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftStatusInfo.remainAmount')}
                id="draft-status-info-remainAmount"
                name="remainAmount"
                data-cy="remainAmount"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftStatusInfo.stampCostPaid')}
                id="draft-status-info-stampCostPaid"
                name="stampCostPaid"
                data-cy="stampCostPaid"
                check
                type="checkbox"
              />
              <ValidatedField
                id="draft-status-info-status"
                name="status"
                data-cy="status"
                label={translate('tfbitaApp.draftStatusInfo.status')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/draft-status-info" replace color="info">
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

export default DraftStatusInfoUpdate;
