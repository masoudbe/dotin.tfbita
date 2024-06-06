import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IOrderRegistrationInfo } from 'app/shared/model/order-registration-info.model';
import { getEntities as getOrderRegistrationInfos } from 'app/entities/order-registration-info/order-registration-info.reducer';
import { IPurchaseFromOtherResources } from 'app/shared/model/purchase-from-other-resources.model';
import { getEntities as getPurchaseFromOtherResources } from 'app/entities/purchase-from-other-resources/purchase-from-other-resources.reducer';
import { ICategoryElement } from 'app/shared/model/category-element.model';
import { getEntity, updateEntity, createEntity, reset } from './category-element.reducer';

export const CategoryElementUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const orderRegistrationInfos = useAppSelector(state => state.orderRegistrationInfo.entities);
  const purchaseFromOtherResources = useAppSelector(state => state.purchaseFromOtherResources.entities);
  const categoryElementEntity = useAppSelector(state => state.categoryElement.entity);
  const loading = useAppSelector(state => state.categoryElement.loading);
  const updating = useAppSelector(state => state.categoryElement.updating);
  const updateSuccess = useAppSelector(state => state.categoryElement.updateSuccess);

  const handleClose = () => {
    navigate('/category-element');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getOrderRegistrationInfos({}));
    dispatch(getPurchaseFromOtherResources({}));
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
      ...categoryElementEntity,
      ...values,
      orderRegType: orderRegistrationInfos.find(it => it.id.toString() === values.orderRegType?.toString()),
      requestType: orderRegistrationInfos.find(it => it.id.toString() === values.requestType?.toString()),
      importType: orderRegistrationInfos.find(it => it.id.toString() === values.importType?.toString()),
      operationTyp: orderRegistrationInfos.find(it => it.id.toString() === values.operationTyp?.toString()),
      currencyProvisionType: orderRegistrationInfos.find(it => it.id.toString() === values.currencyProvisionType?.toString()),
      paymentTool: orderRegistrationInfos.find(it => it.id.toString() === values.paymentTool?.toString()),
      activityType: orderRegistrationInfos.find(it => it.id.toString() === values.activityType?.toString()),
      ownerType: orderRegistrationInfos.find(it => it.id.toString() === values.ownerType?.toString()),
      status: orderRegistrationInfos.find(it => it.id.toString() === values.status?.toString()),
      externalCustomerType: orderRegistrationInfos.find(it => it.id.toString() === values.externalCustomerType?.toString()),
      transportType: orderRegistrationInfos.find(it => it.id.toString() === values.transportType?.toString()),
      currencySupplier: purchaseFromOtherResources.find(it => it.id.toString() === values.currencySupplier?.toString()),
      statusPurchase: purchaseFromOtherResources.find(it => it.id.toString() === values.statusPurchase?.toString()),
      transportVehicleType: orderRegistrationInfos.find(it => it.id.toString() === values.transportVehicleType?.toString()),
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
          ...categoryElementEntity,
          orderRegType: categoryElementEntity?.orderRegType?.id,
          requestType: categoryElementEntity?.requestType?.id,
          importType: categoryElementEntity?.importType?.id,
          operationTyp: categoryElementEntity?.operationTyp?.id,
          currencyProvisionType: categoryElementEntity?.currencyProvisionType?.id,
          paymentTool: categoryElementEntity?.paymentTool?.id,
          activityType: categoryElementEntity?.activityType?.id,
          ownerType: categoryElementEntity?.ownerType?.id,
          status: categoryElementEntity?.status?.id,
          externalCustomerType: categoryElementEntity?.externalCustomerType?.id,
          transportType: categoryElementEntity?.transportType?.id,
          currencySupplier: categoryElementEntity?.currencySupplier?.id,
          statusPurchase: categoryElementEntity?.statusPurchase?.id,
          transportVehicleType: categoryElementEntity?.transportVehicleType?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.categoryElement.home.createOrEditLabel" data-cy="CategoryElementCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.categoryElement.home.createOrEditLabel">Create or edit a CategoryElement</Translate>
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
                  id="category-element-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.categoryElement.value')}
                id="category-element-value"
                name="value"
                data-cy="value"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.categoryElement.categoryName')}
                id="category-element-categoryName"
                name="categoryName"
                data-cy="categoryName"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.categoryElement.code')}
                id="category-element-code"
                name="code"
                data-cy="code"
                type="text"
              />
              <ValidatedField
                id="category-element-orderRegType"
                name="orderRegType"
                data-cy="orderRegType"
                label={translate('tfbitaApp.categoryElement.orderRegType')}
                type="select"
              >
                <option value="" key="0" />
                {orderRegistrationInfos
                  ? orderRegistrationInfos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="category-element-requestType"
                name="requestType"
                data-cy="requestType"
                label={translate('tfbitaApp.categoryElement.requestType')}
                type="select"
              >
                <option value="" key="0" />
                {orderRegistrationInfos
                  ? orderRegistrationInfos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="category-element-importType"
                name="importType"
                data-cy="importType"
                label={translate('tfbitaApp.categoryElement.importType')}
                type="select"
              >
                <option value="" key="0" />
                {orderRegistrationInfos
                  ? orderRegistrationInfos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="category-element-operationTyp"
                name="operationTyp"
                data-cy="operationTyp"
                label={translate('tfbitaApp.categoryElement.operationTyp')}
                type="select"
              >
                <option value="" key="0" />
                {orderRegistrationInfos
                  ? orderRegistrationInfos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="category-element-currencyProvisionType"
                name="currencyProvisionType"
                data-cy="currencyProvisionType"
                label={translate('tfbitaApp.categoryElement.currencyProvisionType')}
                type="select"
              >
                <option value="" key="0" />
                {orderRegistrationInfos
                  ? orderRegistrationInfos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="category-element-paymentTool"
                name="paymentTool"
                data-cy="paymentTool"
                label={translate('tfbitaApp.categoryElement.paymentTool')}
                type="select"
              >
                <option value="" key="0" />
                {orderRegistrationInfos
                  ? orderRegistrationInfos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="category-element-activityType"
                name="activityType"
                data-cy="activityType"
                label={translate('tfbitaApp.categoryElement.activityType')}
                type="select"
              >
                <option value="" key="0" />
                {orderRegistrationInfos
                  ? orderRegistrationInfos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="category-element-ownerType"
                name="ownerType"
                data-cy="ownerType"
                label={translate('tfbitaApp.categoryElement.ownerType')}
                type="select"
              >
                <option value="" key="0" />
                {orderRegistrationInfos
                  ? orderRegistrationInfos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="category-element-status"
                name="status"
                data-cy="status"
                label={translate('tfbitaApp.categoryElement.status')}
                type="select"
              >
                <option value="" key="0" />
                {orderRegistrationInfos
                  ? orderRegistrationInfos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="category-element-externalCustomerType"
                name="externalCustomerType"
                data-cy="externalCustomerType"
                label={translate('tfbitaApp.categoryElement.externalCustomerType')}
                type="select"
              >
                <option value="" key="0" />
                {orderRegistrationInfos
                  ? orderRegistrationInfos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="category-element-transportType"
                name="transportType"
                data-cy="transportType"
                label={translate('tfbitaApp.categoryElement.transportType')}
                type="select"
              >
                <option value="" key="0" />
                {orderRegistrationInfos
                  ? orderRegistrationInfos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="category-element-currencySupplier"
                name="currencySupplier"
                data-cy="currencySupplier"
                label={translate('tfbitaApp.categoryElement.currencySupplier')}
                type="select"
              >
                <option value="" key="0" />
                {purchaseFromOtherResources
                  ? purchaseFromOtherResources.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="category-element-statusPurchase"
                name="statusPurchase"
                data-cy="statusPurchase"
                label={translate('tfbitaApp.categoryElement.statusPurchase')}
                type="select"
              >
                <option value="" key="0" />
                {purchaseFromOtherResources
                  ? purchaseFromOtherResources.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="category-element-transportVehicleType"
                name="transportVehicleType"
                data-cy="transportVehicleType"
                label={translate('tfbitaApp.categoryElement.transportVehicleType')}
                type="select"
              >
                <option value="" key="0" />
                {orderRegistrationInfos
                  ? orderRegistrationInfos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/category-element" replace color="info">
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

export default CategoryElementUpdate;
