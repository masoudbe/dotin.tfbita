import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ICreditTypeCondition } from 'app/shared/model/credit-type-condition.model';
import { getEntities as getCreditTypeConditions } from 'app/entities/credit-type-condition/credit-type-condition.reducer';
import { ICreditTypeConditionInfo } from 'app/shared/model/credit-type-condition-info.model';
import { getEntity, updateEntity, createEntity, reset } from './credit-type-condition-info.reducer';

export const CreditTypeConditionInfoUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const creditTypeConditions = useAppSelector(state => state.creditTypeCondition.entities);
  const creditTypeConditionInfoEntity = useAppSelector(state => state.creditTypeConditionInfo.entity);
  const loading = useAppSelector(state => state.creditTypeConditionInfo.loading);
  const updating = useAppSelector(state => state.creditTypeConditionInfo.updating);
  const updateSuccess = useAppSelector(state => state.creditTypeConditionInfo.updateSuccess);

  const handleClose = () => {
    navigate('/credit-type-condition-info');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getCreditTypeConditions({}));
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
    if (values.durationFrom !== undefined && typeof values.durationFrom !== 'number') {
      values.durationFrom = Number(values.durationFrom);
    }
    if (values.durationTo !== undefined && typeof values.durationTo !== 'number') {
      values.durationTo = Number(values.durationTo);
    }
    if (values.priceFrom !== undefined && typeof values.priceFrom !== 'number') {
      values.priceFrom = Number(values.priceFrom);
    }
    if (values.priceTo !== undefined && typeof values.priceTo !== 'number') {
      values.priceTo = Number(values.priceTo);
    }
    if (values.justificationDisciplinaryTopic !== undefined && typeof values.justificationDisciplinaryTopic !== 'number') {
      values.justificationDisciplinaryTopic = Number(values.justificationDisciplinaryTopic);
    }
    if (values.openDraftDisciplinaryTopic !== undefined && typeof values.openDraftDisciplinaryTopic !== 'number') {
      values.openDraftDisciplinaryTopic = Number(values.openDraftDisciplinaryTopic);
    }
    if (values.otherCostsTopic !== undefined && typeof values.otherCostsTopic !== 'number') {
      values.otherCostsTopic = Number(values.otherCostsTopic);
    }
    if (values.postTelegraphSwiftCostsTopic !== undefined && typeof values.postTelegraphSwiftCostsTopic !== 'number') {
      values.postTelegraphSwiftCostsTopic = Number(values.postTelegraphSwiftCostsTopic);
    }

    const entity = {
      ...creditTypeConditionInfoEntity,
      ...values,
      defaultCondition: creditTypeConditions.find(it => it.id.toString() === values.defaultCondition?.toString()),
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
          ...creditTypeConditionInfoEntity,
          defaultCondition: creditTypeConditionInfoEntity?.defaultCondition?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.creditTypeConditionInfo.home.createOrEditLabel" data-cy="CreditTypeConditionInfoCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.creditTypeConditionInfo.home.createOrEditLabel">
              Create or edit a CreditTypeConditionInfo
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
                  id="credit-type-condition-info-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.creditTypeConditionInfo.durationFrom')}
                id="credit-type-condition-info-durationFrom"
                name="durationFrom"
                data-cy="durationFrom"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.creditTypeConditionInfo.durationTo')}
                id="credit-type-condition-info-durationTo"
                name="durationTo"
                data-cy="durationTo"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.creditTypeConditionInfo.priceFrom')}
                id="credit-type-condition-info-priceFrom"
                name="priceFrom"
                data-cy="priceFrom"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.creditTypeConditionInfo.priceTo')}
                id="credit-type-condition-info-priceTo"
                name="priceTo"
                data-cy="priceTo"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.creditTypeConditionInfo.justificationDisciplinaryTopic')}
                id="credit-type-condition-info-justificationDisciplinaryTopic"
                name="justificationDisciplinaryTopic"
                data-cy="justificationDisciplinaryTopic"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.creditTypeConditionInfo.openDraftDisciplinaryTopic')}
                id="credit-type-condition-info-openDraftDisciplinaryTopic"
                name="openDraftDisciplinaryTopic"
                data-cy="openDraftDisciplinaryTopic"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.creditTypeConditionInfo.otherCostsTopic')}
                id="credit-type-condition-info-otherCostsTopic"
                name="otherCostsTopic"
                data-cy="otherCostsTopic"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.creditTypeConditionInfo.postTelegraphSwiftCostsTopic')}
                id="credit-type-condition-info-postTelegraphSwiftCostsTopic"
                name="postTelegraphSwiftCostsTopic"
                data-cy="postTelegraphSwiftCostsTopic"
                type="text"
              />
              <ValidatedField
                id="credit-type-condition-info-defaultCondition"
                name="defaultCondition"
                data-cy="defaultCondition"
                label={translate('tfbitaApp.creditTypeConditionInfo.defaultCondition')}
                type="select"
              >
                <option value="" key="0" />
                {creditTypeConditions
                  ? creditTypeConditions.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/credit-type-condition-info" replace color="info">
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

export default CreditTypeConditionInfoUpdate;
