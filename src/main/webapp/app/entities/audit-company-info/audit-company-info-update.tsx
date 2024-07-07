import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IAuditCompanyInfo } from 'app/shared/model/audit-company-info.model';
import { getEntity, updateEntity, createEntity, reset } from './audit-company-info.reducer';

export const AuditCompanyInfoUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const auditCompanyInfoEntity = useAppSelector(state => state.auditCompanyInfo.entity);
  const loading = useAppSelector(state => state.auditCompanyInfo.loading);
  const updating = useAppSelector(state => state.auditCompanyInfo.updating);
  const updateSuccess = useAppSelector(state => state.auditCompanyInfo.updateSuccess);

  const handleClose = () => {
    navigate('/audit-company-info');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }
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
    if (values.tempId !== undefined && typeof values.tempId !== 'number') {
      values.tempId = Number(values.tempId);
    }

    const entity = {
      ...auditCompanyInfoEntity,
      ...values,
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
          ...auditCompanyInfoEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.auditCompanyInfo.home.createOrEditLabel" data-cy="AuditCompanyInfoCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.auditCompanyInfo.home.createOrEditLabel">Create or edit a AuditCompanyInfo</Translate>
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
                  id="audit-company-info-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.auditCompanyInfo.address')}
                id="audit-company-info-address"
                name="address"
                data-cy="address"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.auditCompanyInfo.barCodes')}
                id="audit-company-info-barCodes"
                name="barCodes"
                data-cy="barCodes"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.auditCompanyInfo.dateOfRegistration')}
                id="audit-company-info-dateOfRegistration"
                name="dateOfRegistration"
                data-cy="dateOfRegistration"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.auditCompanyInfo.fax')}
                id="audit-company-info-fax"
                name="fax"
                data-cy="fax"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.auditCompanyInfo.floor')}
                id="audit-company-info-floor"
                name="floor"
                data-cy="floor"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.auditCompanyInfo.inernationalobserviation')}
                id="audit-company-info-inernationalobserviation"
                name="inernationalobserviation"
                data-cy="inernationalobserviation"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.auditCompanyInfo.mainStreet')}
                id="audit-company-info-mainStreet"
                name="mainStreet"
                data-cy="mainStreet"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.auditCompanyInfo.mobile')}
                id="audit-company-info-mobile"
                name="mobile"
                data-cy="mobile"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.auditCompanyInfo.plaque')}
                id="audit-company-info-plaque"
                name="plaque"
                data-cy="plaque"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.auditCompanyInfo.postalCode')}
                id="audit-company-info-postalCode"
                name="postalCode"
                data-cy="postalCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.auditCompanyInfo.registrationNumber')}
                id="audit-company-info-registrationNumber"
                name="registrationNumber"
                data-cy="registrationNumber"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.auditCompanyInfo.telephone')}
                id="audit-company-info-telephone"
                name="telephone"
                data-cy="telephone"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.auditCompanyInfo.tempId')}
                id="audit-company-info-tempId"
                name="tempId"
                data-cy="tempId"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.auditCompanyInfo.theSideStreet')}
                id="audit-company-info-theSideStreet"
                name="theSideStreet"
                data-cy="theSideStreet"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.auditCompanyInfo.title')}
                id="audit-company-info-title"
                name="title"
                data-cy="title"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.auditCompanyInfo.unit')}
                id="audit-company-info-unit"
                name="unit"
                data-cy="unit"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.auditCompanyInfo.cityCode')}
                id="audit-company-info-cityCode"
                name="cityCode"
                data-cy="cityCode"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/audit-company-info" replace color="info">
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

export default AuditCompanyInfoUpdate;
