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
import { IObjectiveCategoryElement } from 'app/shared/model/objective-category-element.model';
import { getEntities as getObjectiveCategoryElements } from 'app/entities/objective-category-element/objective-category-element.reducer';
import { ICreditTypeConditionInfo } from 'app/shared/model/credit-type-condition-info.model';
import { getEntities as getCreditTypeConditionInfos } from 'app/entities/credit-type-condition-info/credit-type-condition-info.reducer';
import { ICreditTypeCondition } from 'app/shared/model/credit-type-condition.model';
import { getEntity, updateEntity, createEntity, reset } from './credit-type-condition.reducer';

export const CreditTypeConditionUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const categoryElements = useAppSelector(state => state.categoryElement.entities);
  const objectiveCategoryElements = useAppSelector(state => state.objectiveCategoryElement.entities);
  const creditTypeConditionInfos = useAppSelector(state => state.creditTypeConditionInfo.entities);
  const creditTypeConditionEntity = useAppSelector(state => state.creditTypeCondition.entity);
  const loading = useAppSelector(state => state.creditTypeCondition.loading);
  const updating = useAppSelector(state => state.creditTypeCondition.updating);
  const updateSuccess = useAppSelector(state => state.creditTypeCondition.updateSuccess);

  const handleClose = () => {
    navigate('/credit-type-condition');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getCategoryElements({}));
    dispatch(getObjectiveCategoryElements({}));
    dispatch(getCreditTypeConditionInfos({}));
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
    if (values.assurancePercentage !== undefined && typeof values.assurancePercentage !== 'number') {
      values.assurancePercentage = Number(values.assurancePercentage);
    }
    if (values.commissionRate !== undefined && typeof values.commissionRate !== 'number') {
      values.commissionRate = Number(values.commissionRate);
    }
    if (values.customerPrepaymentRateFrom !== undefined && typeof values.customerPrepaymentRateFrom !== 'number') {
      values.customerPrepaymentRateFrom = Number(values.customerPrepaymentRateFrom);
    }
    if (values.customerPrepaymentRateTo !== undefined && typeof values.customerPrepaymentRateTo !== 'number') {
      values.customerPrepaymentRateTo = Number(values.customerPrepaymentRateTo);
    }
    if (values.durationFrom !== undefined && typeof values.durationFrom !== 'number') {
      values.durationFrom = Number(values.durationFrom);
    }
    if (values.durationTo !== undefined && typeof values.durationTo !== 'number') {
      values.durationTo = Number(values.durationTo);
    }
    if (values.orderRegistrationRightFrom !== undefined && typeof values.orderRegistrationRightFrom !== 'number') {
      values.orderRegistrationRightFrom = Number(values.orderRegistrationRightFrom);
    }
    if (values.orderRegistrationRightTo !== undefined && typeof values.orderRegistrationRightTo !== 'number') {
      values.orderRegistrationRightTo = Number(values.orderRegistrationRightTo);
    }
    if (values.postSuspensionPeriodPenaltyRate !== undefined && typeof values.postSuspensionPeriodPenaltyRate !== 'number') {
      values.postSuspensionPeriodPenaltyRate = Number(values.postSuspensionPeriodPenaltyRate);
    }
    if (values.priceFrom !== undefined && typeof values.priceFrom !== 'number') {
      values.priceFrom = Number(values.priceFrom);
    }
    if (values.priceTo !== undefined && typeof values.priceTo !== 'number') {
      values.priceTo = Number(values.priceTo);
    }
    if (values.suspensionDurationFrom !== undefined && typeof values.suspensionDurationFrom !== 'number') {
      values.suspensionDurationFrom = Number(values.suspensionDurationFrom);
    }
    if (values.suspensionDurationTo !== undefined && typeof values.suspensionDurationTo !== 'number') {
      values.suspensionDurationTo = Number(values.suspensionDurationTo);
    }
    if (values.suspensionPeriodInterestRate !== undefined && typeof values.suspensionPeriodInterestRate !== 'number') {
      values.suspensionPeriodInterestRate = Number(values.suspensionPeriodInterestRate);
    }
    if (values.updateCommissionRate !== undefined && typeof values.updateCommissionRate !== 'number') {
      values.updateCommissionRate = Number(values.updateCommissionRate);
    }

    const entity = {
      ...creditTypeConditionEntity,
      ...values,
      serviceOrProduct: categoryElements.find(it => it.id.toString() === values.serviceOrProduct?.toString()),
      neededIdentificationDocTypes: objectiveCategoryElements.find(
        it => it.id.toString() === values.neededIdentificationDocTypes?.toString(),
      ),
      productTypes: objectiveCategoryElements.find(it => it.id.toString() === values.productTypes?.toString()),
      assuranceTypes: objectiveCategoryElements.find(it => it.id.toString() === values.assuranceTypes?.toString()),
      creditTypeConditionInfo: creditTypeConditionInfos.find(it => it.id.toString() === values.creditTypeConditionInfo?.toString()),
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
          ...creditTypeConditionEntity,
          serviceOrProduct: creditTypeConditionEntity?.serviceOrProduct?.id,
          neededIdentificationDocTypes: creditTypeConditionEntity?.neededIdentificationDocTypes?.id,
          productTypes: creditTypeConditionEntity?.productTypes?.id,
          assuranceTypes: creditTypeConditionEntity?.assuranceTypes?.id,
          creditTypeConditionInfo: creditTypeConditionEntity?.creditTypeConditionInfo?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.creditTypeCondition.home.createOrEditLabel" data-cy="CreditTypeConditionCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.creditTypeCondition.home.createOrEditLabel">Create or edit a CreditTypeCondition</Translate>
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
                  id="credit-type-condition-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.creditTypeCondition.assurancePercentage')}
                id="credit-type-condition-assurancePercentage"
                name="assurancePercentage"
                data-cy="assurancePercentage"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.creditTypeCondition.commissionRate')}
                id="credit-type-condition-commissionRate"
                name="commissionRate"
                data-cy="commissionRate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.creditTypeCondition.customerPrepaymentRateFrom')}
                id="credit-type-condition-customerPrepaymentRateFrom"
                name="customerPrepaymentRateFrom"
                data-cy="customerPrepaymentRateFrom"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.creditTypeCondition.customerPrepaymentRateTo')}
                id="credit-type-condition-customerPrepaymentRateTo"
                name="customerPrepaymentRateTo"
                data-cy="customerPrepaymentRateTo"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.creditTypeCondition.durationFrom')}
                id="credit-type-condition-durationFrom"
                name="durationFrom"
                data-cy="durationFrom"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.creditTypeCondition.durationTo')}
                id="credit-type-condition-durationTo"
                name="durationTo"
                data-cy="durationTo"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.creditTypeCondition.orderRegistrationRightFrom')}
                id="credit-type-condition-orderRegistrationRightFrom"
                name="orderRegistrationRightFrom"
                data-cy="orderRegistrationRightFrom"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.creditTypeCondition.orderRegistrationRightTo')}
                id="credit-type-condition-orderRegistrationRightTo"
                name="orderRegistrationRightTo"
                data-cy="orderRegistrationRightTo"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.creditTypeCondition.postSuspensionPeriodPenaltyRate')}
                id="credit-type-condition-postSuspensionPeriodPenaltyRate"
                name="postSuspensionPeriodPenaltyRate"
                data-cy="postSuspensionPeriodPenaltyRate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.creditTypeCondition.priceFrom')}
                id="credit-type-condition-priceFrom"
                name="priceFrom"
                data-cy="priceFrom"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.creditTypeCondition.priceTo')}
                id="credit-type-condition-priceTo"
                name="priceTo"
                data-cy="priceTo"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.creditTypeCondition.suspensionDurationFrom')}
                id="credit-type-condition-suspensionDurationFrom"
                name="suspensionDurationFrom"
                data-cy="suspensionDurationFrom"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.creditTypeCondition.suspensionDurationTo')}
                id="credit-type-condition-suspensionDurationTo"
                name="suspensionDurationTo"
                data-cy="suspensionDurationTo"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.creditTypeCondition.suspensionPeriodInterestRate')}
                id="credit-type-condition-suspensionPeriodInterestRate"
                name="suspensionPeriodInterestRate"
                data-cy="suspensionPeriodInterestRate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.creditTypeCondition.updateCommissionRate')}
                id="credit-type-condition-updateCommissionRate"
                name="updateCommissionRate"
                data-cy="updateCommissionRate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.creditTypeCondition.currencyCode')}
                id="credit-type-condition-currencyCode"
                name="currencyCode"
                data-cy="currencyCode"
                type="text"
              />
              <ValidatedField
                id="credit-type-condition-serviceOrProduct"
                name="serviceOrProduct"
                data-cy="serviceOrProduct"
                label={translate('tfbitaApp.creditTypeCondition.serviceOrProduct')}
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
                id="credit-type-condition-neededIdentificationDocTypes"
                name="neededIdentificationDocTypes"
                data-cy="neededIdentificationDocTypes"
                label={translate('tfbitaApp.creditTypeCondition.neededIdentificationDocTypes')}
                type="select"
              >
                <option value="" key="0" />
                {objectiveCategoryElements
                  ? objectiveCategoryElements.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="credit-type-condition-productTypes"
                name="productTypes"
                data-cy="productTypes"
                label={translate('tfbitaApp.creditTypeCondition.productTypes')}
                type="select"
              >
                <option value="" key="0" />
                {objectiveCategoryElements
                  ? objectiveCategoryElements.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="credit-type-condition-assuranceTypes"
                name="assuranceTypes"
                data-cy="assuranceTypes"
                label={translate('tfbitaApp.creditTypeCondition.assuranceTypes')}
                type="select"
              >
                <option value="" key="0" />
                {objectiveCategoryElements
                  ? objectiveCategoryElements.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="credit-type-condition-creditTypeConditionInfo"
                name="creditTypeConditionInfo"
                data-cy="creditTypeConditionInfo"
                label={translate('tfbitaApp.creditTypeCondition.creditTypeConditionInfo')}
                type="select"
              >
                <option value="" key="0" />
                {creditTypeConditionInfos
                  ? creditTypeConditionInfos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/credit-type-condition" replace color="info">
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

export default CreditTypeConditionUpdate;
