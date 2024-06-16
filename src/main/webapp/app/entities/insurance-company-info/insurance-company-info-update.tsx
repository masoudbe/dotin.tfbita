import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IDraft } from 'app/shared/model/draft.model';
import { getEntities as getDrafts } from 'app/entities/draft/draft.reducer';
import { IInsuranceCompanyInfo } from 'app/shared/model/insurance-company-info.model';
import { getEntity, updateEntity, createEntity, reset } from './insurance-company-info.reducer';

export const InsuranceCompanyInfoUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const drafts = useAppSelector(state => state.draft.entities);
  const insuranceCompanyInfoEntity = useAppSelector(state => state.insuranceCompanyInfo.entity);
  const loading = useAppSelector(state => state.insuranceCompanyInfo.loading);
  const updating = useAppSelector(state => state.insuranceCompanyInfo.updating);
  const updateSuccess = useAppSelector(state => state.insuranceCompanyInfo.updateSuccess);

  const handleClose = () => {
    navigate('/insurance-company-info');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getDrafts({}));
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
      ...insuranceCompanyInfoEntity,
      ...values,
      insuranceCompanyInfo: drafts.find(it => it.id.toString() === values.insuranceCompanyInfo?.toString()),
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
          ...insuranceCompanyInfoEntity,
          insuranceCompanyInfo: insuranceCompanyInfoEntity?.insuranceCompanyInfo?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.insuranceCompanyInfo.home.createOrEditLabel" data-cy="InsuranceCompanyInfoCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.insuranceCompanyInfo.home.createOrEditLabel">Create or edit a InsuranceCompanyInfo</Translate>
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
                  id="insurance-company-info-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.insuranceCompanyInfo.modificationDate')}
                id="insurance-company-info-modificationDate"
                name="modificationDate"
                data-cy="modificationDate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.insuranceCompanyInfo.name')}
                id="insurance-company-info-name"
                name="name"
                data-cy="name"
                type="text"
              />
              <ValidatedField
                id="insurance-company-info-insuranceCompanyInfo"
                name="insuranceCompanyInfo"
                data-cy="insuranceCompanyInfo"
                label={translate('tfbitaApp.insuranceCompanyInfo.insuranceCompanyInfo')}
                type="select"
              >
                <option value="" key="0" />
                {drafts
                  ? drafts.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/insurance-company-info" replace color="info">
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

export default InsuranceCompanyInfoUpdate;
