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
import { IAdvisorDefinition } from 'app/shared/model/advisor-definition.model';
import { getEntities as getAdvisorDefinitions } from 'app/entities/advisor-definition/advisor-definition.reducer';
import { IAdvisorDefinitionDeposit } from 'app/shared/model/advisor-definition-deposit.model';
import { getEntity, updateEntity, createEntity, reset } from './advisor-definition-deposit.reducer';

export const AdvisorDefinitionDepositUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const categoryElements = useAppSelector(state => state.categoryElement.entities);
  const advisorDefinitions = useAppSelector(state => state.advisorDefinition.entities);
  const advisorDefinitionDepositEntity = useAppSelector(state => state.advisorDefinitionDeposit.entity);
  const loading = useAppSelector(state => state.advisorDefinitionDeposit.loading);
  const updating = useAppSelector(state => state.advisorDefinitionDeposit.updating);
  const updateSuccess = useAppSelector(state => state.advisorDefinitionDeposit.updateSuccess);

  const handleClose = () => {
    navigate('/advisor-definition-deposit');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getCategoryElements({}));
    dispatch(getAdvisorDefinitions({}));
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
      ...advisorDefinitionDepositEntity,
      ...values,
      depositType: categoryElements.find(it => it.id.toString() === values.depositType?.toString()),
      advisorDefinition: advisorDefinitions.find(it => it.id.toString() === values.advisorDefinition?.toString()),
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
          ...advisorDefinitionDepositEntity,
          depositType: advisorDefinitionDepositEntity?.depositType?.id,
          advisorDefinition: advisorDefinitionDepositEntity?.advisorDefinition?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.advisorDefinitionDeposit.home.createOrEditLabel" data-cy="AdvisorDefinitionDepositCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.advisorDefinitionDeposit.home.createOrEditLabel">
              Create or edit a AdvisorDefinitionDeposit
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
                  id="advisor-definition-deposit-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.advisorDefinitionDeposit.advisorDepositNumber')}
                id="advisor-definition-deposit-advisorDepositNumber"
                name="advisorDepositNumber"
                data-cy="advisorDepositNumber"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.advisorDefinitionDeposit.defaultAdvisorDeposit')}
                id="advisor-definition-deposit-defaultAdvisorDeposit"
                name="defaultAdvisorDeposit"
                data-cy="defaultAdvisorDeposit"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.advisorDefinitionDeposit.depositNum')}
                id="advisor-definition-deposit-depositNum"
                name="depositNum"
                data-cy="depositNum"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.advisorDefinitionDeposit.swiftCode')}
                id="advisor-definition-deposit-swiftCode"
                name="swiftCode"
                data-cy="swiftCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.advisorDefinitionDeposit.currencyCode')}
                id="advisor-definition-deposit-currencyCode"
                name="currencyCode"
                data-cy="currencyCode"
                type="text"
              />
              <ValidatedField
                id="advisor-definition-deposit-depositType"
                name="depositType"
                data-cy="depositType"
                label={translate('tfbitaApp.advisorDefinitionDeposit.depositType')}
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
              <ValidatedField
                id="advisor-definition-deposit-advisorDefinition"
                name="advisorDefinition"
                data-cy="advisorDefinition"
                label={translate('tfbitaApp.advisorDefinitionDeposit.advisorDefinition')}
                type="select"
              >
                <option value="" key="0" />
                {advisorDefinitions
                  ? advisorDefinitions.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/advisor-definition-deposit" replace color="info">
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

export default AdvisorDefinitionDepositUpdate;
