import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IDraftRequestType } from 'app/shared/model/draft-request-type.model';
import { getEntities as getDraftRequestTypes } from 'app/entities/draft-request-type/draft-request-type.reducer';
import { IDraftCertificateType } from 'app/shared/model/draft-certificate-type.model';
import { getEntity, updateEntity, createEntity, reset } from './draft-certificate-type.reducer';

export const DraftCertificateTypeUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const draftRequestTypes = useAppSelector(state => state.draftRequestType.entities);
  const draftCertificateTypeEntity = useAppSelector(state => state.draftCertificateType.entity);
  const loading = useAppSelector(state => state.draftCertificateType.loading);
  const updating = useAppSelector(state => state.draftCertificateType.updating);
  const updateSuccess = useAppSelector(state => state.draftCertificateType.updateSuccess);

  const handleClose = () => {
    navigate('/draft-certificate-type');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getDraftRequestTypes({}));
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
      ...draftCertificateTypeEntity,
      ...values,
      draftRequestType: draftRequestTypes.find(it => it.id.toString() === values.draftRequestType?.toString()),
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
          ...draftCertificateTypeEntity,
          draftRequestType: draftCertificateTypeEntity?.draftRequestType?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.draftCertificateType.home.createOrEditLabel" data-cy="DraftCertificateTypeCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.draftCertificateType.home.createOrEditLabel">Create or edit a DraftCertificateType</Translate>
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
                  id="draft-certificate-type-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.draftCertificateType.mandatory')}
                id="draft-certificate-type-mandatory"
                name="mandatory"
                data-cy="mandatory"
                check
                type="checkbox"
              />
              <ValidatedField
                id="draft-certificate-type-draftRequestType"
                name="draftRequestType"
                data-cy="draftRequestType"
                label={translate('tfbitaApp.draftCertificateType.draftRequestType')}
                type="select"
              >
                <option value="" key="0" />
                {draftRequestTypes
                  ? draftRequestTypes.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/draft-certificate-type" replace color="info">
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

export default DraftCertificateTypeUpdate;
