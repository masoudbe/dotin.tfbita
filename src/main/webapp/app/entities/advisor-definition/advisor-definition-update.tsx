import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IAdditionalBrokerInformation } from 'app/shared/model/additional-broker-information.model';
import { getEntities as getAdditionalBrokerInformations } from 'app/entities/additional-broker-information/additional-broker-information.reducer';
import { IAdvisorDefinitionDeposit } from 'app/shared/model/advisor-definition-deposit.model';
import { getEntities as getAdvisorDefinitionDeposits } from 'app/entities/advisor-definition-deposit/advisor-definition-deposit.reducer';
import { ITransferMethodManagement } from 'app/shared/model/transfer-method-management.model';
import { getEntities as getTransferMethodManagements } from 'app/entities/transfer-method-management/transfer-method-management.reducer';
import { ISwiftBic } from 'app/shared/model/swift-bic.model';
import { getEntities as getSwiftBics } from 'app/entities/swift-bic/swift-bic.reducer';
import { IAdvisorDefinition } from 'app/shared/model/advisor-definition.model';
import { getEntity, updateEntity, createEntity, reset } from './advisor-definition.reducer';

export const AdvisorDefinitionUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const additionalBrokerInformations = useAppSelector(state => state.additionalBrokerInformation.entities);
  const advisorDefinitionDeposits = useAppSelector(state => state.advisorDefinitionDeposit.entities);
  const transferMethodManagements = useAppSelector(state => state.transferMethodManagement.entities);
  const swiftBics = useAppSelector(state => state.swiftBic.entities);
  const advisorDefinitionEntity = useAppSelector(state => state.advisorDefinition.entity);
  const loading = useAppSelector(state => state.advisorDefinition.loading);
  const updating = useAppSelector(state => state.advisorDefinition.updating);
  const updateSuccess = useAppSelector(state => state.advisorDefinition.updateSuccess);

  const handleClose = () => {
    navigate('/advisor-definition');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getAdditionalBrokerInformations({}));
    dispatch(getAdvisorDefinitionDeposits({}));
    dispatch(getTransferMethodManagements({}));
    dispatch(getSwiftBics({}));
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
      ...advisorDefinitionEntity,
      ...values,
      additionalBrokerInformation: additionalBrokerInformations.find(
        it => it.id.toString() === values.additionalBrokerInformation?.toString(),
      ),
      defaultVostroDeposit: advisorDefinitionDeposits.find(it => it.id.toString() === values.defaultVostroDeposit?.toString()),
      defaultNostroDeposit: advisorDefinitionDeposits.find(it => it.id.toString() === values.defaultNostroDeposit?.toString()),
      receiveMethod: transferMethodManagements.find(it => it.id.toString() === values.receiveMethod?.toString()),
      payMethod: transferMethodManagements.find(it => it.id.toString() === values.payMethod?.toString()),
      swiftBic: swiftBics.find(it => it.id.toString() === values.swiftBic?.toString()),
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
          ...advisorDefinitionEntity,
          additionalBrokerInformation: advisorDefinitionEntity?.additionalBrokerInformation?.id,
          defaultVostroDeposit: advisorDefinitionEntity?.defaultVostroDeposit?.id,
          defaultNostroDeposit: advisorDefinitionEntity?.defaultNostroDeposit?.id,
          receiveMethod: advisorDefinitionEntity?.receiveMethod?.id,
          payMethod: advisorDefinitionEntity?.payMethod?.id,
          swiftBic: advisorDefinitionEntity?.swiftBic?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.advisorDefinition.home.createOrEditLabel" data-cy="AdvisorDefinitionCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.advisorDefinition.home.createOrEditLabel">Create or edit a AdvisorDefinition</Translate>
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
                  id="advisor-definition-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.advisorDefinition.caption')}
                id="advisor-definition-caption"
                name="caption"
                data-cy="caption"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.advisorDefinition.code')}
                id="advisor-definition-code"
                name="code"
                data-cy="code"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.advisorDefinition.countryIsoCode')}
                id="advisor-definition-countryIsoCode"
                name="countryIsoCode"
                data-cy="countryIsoCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.advisorDefinition.depositNum')}
                id="advisor-definition-depositNum"
                name="depositNum"
                data-cy="depositNum"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.advisorDefinition.swiftCode')}
                id="advisor-definition-swiftCode"
                name="swiftCode"
                data-cy="swiftCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.advisorDefinition.defaultCurrencyCode')}
                id="advisor-definition-defaultCurrencyCode"
                name="defaultCurrencyCode"
                data-cy="defaultCurrencyCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.advisorDefinition.currenciesCodes')}
                id="advisor-definition-currenciesCodes"
                name="currenciesCodes"
                data-cy="currenciesCodes"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.advisorDefinition.countryCode')}
                id="advisor-definition-countryCode"
                name="countryCode"
                data-cy="countryCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.advisorDefinition.bankCode')}
                id="advisor-definition-bankCode"
                name="bankCode"
                data-cy="bankCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.advisorDefinition.branchCode')}
                id="advisor-definition-branchCode"
                name="branchCode"
                data-cy="branchCode"
                type="text"
              />
              <ValidatedField
                id="advisor-definition-additionalBrokerInformation"
                name="additionalBrokerInformation"
                data-cy="additionalBrokerInformation"
                label={translate('tfbitaApp.advisorDefinition.additionalBrokerInformation')}
                type="select"
              >
                <option value="" key="0" />
                {additionalBrokerInformations
                  ? additionalBrokerInformations.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="advisor-definition-defaultVostroDeposit"
                name="defaultVostroDeposit"
                data-cy="defaultVostroDeposit"
                label={translate('tfbitaApp.advisorDefinition.defaultVostroDeposit')}
                type="select"
              >
                <option value="" key="0" />
                {advisorDefinitionDeposits
                  ? advisorDefinitionDeposits.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="advisor-definition-defaultNostroDeposit"
                name="defaultNostroDeposit"
                data-cy="defaultNostroDeposit"
                label={translate('tfbitaApp.advisorDefinition.defaultNostroDeposit')}
                type="select"
              >
                <option value="" key="0" />
                {advisorDefinitionDeposits
                  ? advisorDefinitionDeposits.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="advisor-definition-receiveMethod"
                name="receiveMethod"
                data-cy="receiveMethod"
                label={translate('tfbitaApp.advisorDefinition.receiveMethod')}
                type="select"
              >
                <option value="" key="0" />
                {transferMethodManagements
                  ? transferMethodManagements.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="advisor-definition-payMethod"
                name="payMethod"
                data-cy="payMethod"
                label={translate('tfbitaApp.advisorDefinition.payMethod')}
                type="select"
              >
                <option value="" key="0" />
                {transferMethodManagements
                  ? transferMethodManagements.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="advisor-definition-swiftBic"
                name="swiftBic"
                data-cy="swiftBic"
                label={translate('tfbitaApp.advisorDefinition.swiftBic')}
                type="select"
              >
                <option value="" key="0" />
                {swiftBics
                  ? swiftBics.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/advisor-definition" replace color="info">
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

export default AdvisorDefinitionUpdate;
