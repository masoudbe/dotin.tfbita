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
import { IAdvisorDefinition } from 'app/shared/model/advisor-definition.model';
import { getEntity, updateEntity, createEntity, reset } from './advisor-definition.reducer';

export const AdvisorDefinitionUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const drafts = useAppSelector(state => state.draft.entities);
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
      ...advisorDefinitionEntity,
      ...values,
      advisingBank: drafts.find(it => it.id.toString() === values.advisingBank?.toString()),
      interfaceAdvisingBank: drafts.find(it => it.id.toString() === values.interfaceAdvisingBank?.toString()),
      coveringBank: drafts.find(it => it.id.toString() === values.coveringBank?.toString()),
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
          advisingBank: advisorDefinitionEntity?.advisingBank?.id,
          interfaceAdvisingBank: advisorDefinitionEntity?.interfaceAdvisingBank?.id,
          coveringBank: advisorDefinitionEntity?.coveringBank?.id,
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
                label={translate('tfbitaApp.advisorDefinition.creditDate')}
                id="advisor-definition-creditDate"
                name="creditDate"
                data-cy="creditDate"
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
                label={translate('tfbitaApp.advisorDefinition.defaultCurrencyCode')}
                id="advisor-definition-defaultCurrencyCode"
                name="defaultCurrencyCode"
                data-cy="defaultCurrencyCode"
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
                id="advisor-definition-advisingBank"
                name="advisingBank"
                data-cy="advisingBank"
                label={translate('tfbitaApp.advisorDefinition.advisingBank')}
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
              <ValidatedField
                id="advisor-definition-interfaceAdvisingBank"
                name="interfaceAdvisingBank"
                data-cy="interfaceAdvisingBank"
                label={translate('tfbitaApp.advisorDefinition.interfaceAdvisingBank')}
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
              <ValidatedField
                id="advisor-definition-coveringBank"
                name="coveringBank"
                data-cy="coveringBank"
                label={translate('tfbitaApp.advisorDefinition.coveringBank')}
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
