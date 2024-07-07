import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IAdditionalBrokerInformation } from 'app/shared/model/additional-broker-information.model';
import { getEntity, updateEntity, createEntity, reset } from './additional-broker-information.reducer';

export const AdditionalBrokerInformationUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const additionalBrokerInformationEntity = useAppSelector(state => state.additionalBrokerInformation.entity);
  const loading = useAppSelector(state => state.additionalBrokerInformation.loading);
  const updating = useAppSelector(state => state.additionalBrokerInformation.updating);
  const updateSuccess = useAppSelector(state => state.additionalBrokerInformation.updateSuccess);

  const handleClose = () => {
    navigate('/additional-broker-information');
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

    const entity = {
      ...additionalBrokerInformationEntity,
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
          ...additionalBrokerInformationEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.additionalBrokerInformation.home.createOrEditLabel" data-cy="AdditionalBrokerInformationCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.additionalBrokerInformation.home.createOrEditLabel">
              Create or edit a AdditionalBrokerInformation
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
                  id="additional-broker-information-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.additionalBrokerInformation.dateOfStartRelation')}
                id="additional-broker-information-dateOfStartRelation"
                name="dateOfStartRelation"
                data-cy="dateOfStartRelation"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.additionalBrokerInformation.creditLimit')}
                id="additional-broker-information-creditLimit"
                name="creditLimit"
                data-cy="creditLimit"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.additionalBrokerInformation.revokedDate')}
                id="additional-broker-information-revokedDate"
                name="revokedDate"
                data-cy="revokedDate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.additionalBrokerInformation.revokedNote')}
                id="additional-broker-information-revokedNote"
                name="revokedNote"
                data-cy="revokedNote"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.additionalBrokerInformation.confidential')}
                id="additional-broker-information-confidential"
                name="confidential"
                data-cy="confidential"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.additionalBrokerInformation.otherBrokerServices')}
                id="additional-broker-information-otherBrokerServices"
                name="otherBrokerServices"
                data-cy="otherBrokerServices"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.additionalBrokerInformation.sanctionedStatus')}
                id="additional-broker-information-sanctionedStatus"
                name="sanctionedStatus"
                data-cy="sanctionedStatus"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.additionalBrokerInformation.considerations')}
                id="additional-broker-information-considerations"
                name="considerations"
                data-cy="considerations"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.additionalBrokerInformation.description')}
                id="additional-broker-information-description"
                name="description"
                data-cy="description"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.additionalBrokerInformation.waysOfCommunication')}
                id="additional-broker-information-waysOfCommunication"
                name="waysOfCommunication"
                data-cy="waysOfCommunication"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.additionalBrokerInformation.servicesAvailable')}
                id="additional-broker-information-servicesAvailable"
                name="servicesAvailable"
                data-cy="servicesAvailable"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.additionalBrokerInformation.customerAcceptancePolicy')}
                id="additional-broker-information-customerAcceptancePolicy"
                name="customerAcceptancePolicy"
                data-cy="customerAcceptancePolicy"
                type="text"
              />
              <Button
                tag={Link}
                id="cancel-save"
                data-cy="entityCreateCancelButton"
                to="/additional-broker-information"
                replace
                color="info"
              >
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

export default AdditionalBrokerInformationUpdate;
