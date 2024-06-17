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
import { IDraft } from 'app/shared/model/draft.model';
import { getEntities as getDrafts } from 'app/entities/draft/draft.reducer';
import { IAdvisorDefinition } from 'app/shared/model/advisor-definition.model';
import { getEntities as getAdvisorDefinitions } from 'app/entities/advisor-definition/advisor-definition.reducer';
import { IDraftType } from 'app/shared/model/draft-type.model';
import { getEntities as getDraftTypes } from 'app/entities/draft-type/draft-type.reducer';
import { IDraftReceipt } from 'app/shared/model/draft-receipt.model';
import { getEntities as getDraftReceipts } from 'app/entities/draft-receipt/draft-receipt.reducer';
import { IDraftStatusInfo } from 'app/shared/model/draft-status-info.model';
import { getEntities as getDraftStatusInfos } from 'app/entities/draft-status-info/draft-status-info.reducer';
import { ICategory } from 'app/shared/model/category.model';
import { getEntities as getCategories } from 'app/entities/category/category.reducer';
import { ICategoryElement } from 'app/shared/model/category-element.model';
import { getEntity, updateEntity, createEntity, reset } from './category-element.reducer';

export const CategoryElementUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const orderRegistrationInfos = useAppSelector(state => state.orderRegistrationInfo.entities);
  const purchaseFromOtherResources = useAppSelector(state => state.purchaseFromOtherResources.entities);
  const drafts = useAppSelector(state => state.draft.entities);
  const advisorDefinitions = useAppSelector(state => state.advisorDefinition.entities);
  const draftTypes = useAppSelector(state => state.draftType.entities);
  const draftReceipts = useAppSelector(state => state.draftReceipt.entities);
  const draftStatusInfos = useAppSelector(state => state.draftStatusInfo.entities);
  const categories = useAppSelector(state => state.category.entities);
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
    dispatch(getDrafts({}));
    dispatch(getAdvisorDefinitions({}));
    dispatch(getDraftTypes({}));
    dispatch(getDraftReceipts({}));
    dispatch(getDraftStatusInfos({}));
    dispatch(getCategories({}));
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
      freightLetterType: drafts.find(it => it.id.toString() === values.freightLetterType?.toString()),
      actionCode: drafts.find(it => it.id.toString() === values.actionCode?.toString()),
      ownershipCode: drafts.find(it => it.id.toString() === values.ownershipCode?.toString()),
      currencyContainerPlace: drafts.find(it => it.id.toString() === values.currencyContainerPlace?.toString()),
      draftSource: drafts.find(it => it.id.toString() === values.draftSource?.toString()),
      chargedExchangeBroker: drafts.find(it => it.id.toString() === values.chargedExchangeBroker?.toString()),
      impartType: drafts.find(it => it.id.toString() === values.impartType?.toString()),
      insuranceLetterType: drafts.find(it => it.id.toString() === values.insuranceLetterType?.toString()),
      advisorDepositType: drafts.find(it => it.id.toString() === values.advisorDepositType?.toString()),
      interfaceAdvisorDepositType: drafts.find(it => it.id.toString() === values.interfaceAdvisorDepositType?.toString()),
      paymentType: drafts.find(it => it.id.toString() === values.paymentType?.toString()),
      dealType: drafts.find(it => it.id.toString() === values.dealType?.toString()),
      coveringAdvisorDepositType: drafts.find(it => it.id.toString() === values.coveringAdvisorDepositType?.toString()),
      depositType: advisorDefinitions.find(it => it.id.toString() === values.depositType?.toString()),
      type: draftTypes.find(it => it.id.toString() === values.type?.toString()),
      secondaryType: draftTypes.find(it => it.id.toString() === values.secondaryType?.toString()),
      division: draftTypes.find(it => it.id.toString() === values.division?.toString()),
      productDimension: draftReceipts.find(it => it.id.toString() === values.productDimension?.toString()),
      stateOfDocuments: draftReceipts.find(it => it.id.toString() === values.stateOfDocuments?.toString()),
      currencyProvisionFileType: draftReceipts.find(it => it.id.toString() === values.currencyProvisionFileType?.toString()),
      statusDraft: draftStatusInfos.find(it => it.id.toString() === values.statusDraft?.toString()),
      categoryElement: categories.find(it => it.id.toString() === values.categoryElement?.toString()),
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
          freightLetterType: categoryElementEntity?.freightLetterType?.id,
          actionCode: categoryElementEntity?.actionCode?.id,
          ownershipCode: categoryElementEntity?.ownershipCode?.id,
          currencyContainerPlace: categoryElementEntity?.currencyContainerPlace?.id,
          draftSource: categoryElementEntity?.draftSource?.id,
          chargedExchangeBroker: categoryElementEntity?.chargedExchangeBroker?.id,
          impartType: categoryElementEntity?.impartType?.id,
          insuranceLetterType: categoryElementEntity?.insuranceLetterType?.id,
          advisorDepositType: categoryElementEntity?.advisorDepositType?.id,
          interfaceAdvisorDepositType: categoryElementEntity?.interfaceAdvisorDepositType?.id,
          paymentType: categoryElementEntity?.paymentType?.id,
          dealType: categoryElementEntity?.dealType?.id,
          coveringAdvisorDepositType: categoryElementEntity?.coveringAdvisorDepositType?.id,
          depositType: categoryElementEntity?.depositType?.id,
          type: categoryElementEntity?.type?.id,
          secondaryType: categoryElementEntity?.secondaryType?.id,
          division: categoryElementEntity?.division?.id,
          productDimension: categoryElementEntity?.productDimension?.id,
          stateOfDocuments: categoryElementEntity?.stateOfDocuments?.id,
          currencyProvisionFileType: categoryElementEntity?.currencyProvisionFileType?.id,
          statusDraft: categoryElementEntity?.statusDraft?.id,
          categoryElement: categoryElementEntity?.categoryElement?.id,
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
              <ValidatedField
                id="category-element-freightLetterType"
                name="freightLetterType"
                data-cy="freightLetterType"
                label={translate('tfbitaApp.categoryElement.freightLetterType')}
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
                id="category-element-actionCode"
                name="actionCode"
                data-cy="actionCode"
                label={translate('tfbitaApp.categoryElement.actionCode')}
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
                id="category-element-ownershipCode"
                name="ownershipCode"
                data-cy="ownershipCode"
                label={translate('tfbitaApp.categoryElement.ownershipCode')}
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
                id="category-element-currencyContainerPlace"
                name="currencyContainerPlace"
                data-cy="currencyContainerPlace"
                label={translate('tfbitaApp.categoryElement.currencyContainerPlace')}
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
                id="category-element-draftSource"
                name="draftSource"
                data-cy="draftSource"
                label={translate('tfbitaApp.categoryElement.draftSource')}
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
                id="category-element-chargedExchangeBroker"
                name="chargedExchangeBroker"
                data-cy="chargedExchangeBroker"
                label={translate('tfbitaApp.categoryElement.chargedExchangeBroker')}
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
                id="category-element-impartType"
                name="impartType"
                data-cy="impartType"
                label={translate('tfbitaApp.categoryElement.impartType')}
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
                id="category-element-insuranceLetterType"
                name="insuranceLetterType"
                data-cy="insuranceLetterType"
                label={translate('tfbitaApp.categoryElement.insuranceLetterType')}
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
                id="category-element-advisorDepositType"
                name="advisorDepositType"
                data-cy="advisorDepositType"
                label={translate('tfbitaApp.categoryElement.advisorDepositType')}
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
                id="category-element-interfaceAdvisorDepositType"
                name="interfaceAdvisorDepositType"
                data-cy="interfaceAdvisorDepositType"
                label={translate('tfbitaApp.categoryElement.interfaceAdvisorDepositType')}
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
                id="category-element-paymentType"
                name="paymentType"
                data-cy="paymentType"
                label={translate('tfbitaApp.categoryElement.paymentType')}
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
                id="category-element-dealType"
                name="dealType"
                data-cy="dealType"
                label={translate('tfbitaApp.categoryElement.dealType')}
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
                id="category-element-coveringAdvisorDepositType"
                name="coveringAdvisorDepositType"
                data-cy="coveringAdvisorDepositType"
                label={translate('tfbitaApp.categoryElement.coveringAdvisorDepositType')}
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
                id="category-element-depositType"
                name="depositType"
                data-cy="depositType"
                label={translate('tfbitaApp.categoryElement.depositType')}
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
              <ValidatedField
                id="category-element-type"
                name="type"
                data-cy="type"
                label={translate('tfbitaApp.categoryElement.type')}
                type="select"
              >
                <option value="" key="0" />
                {draftTypes
                  ? draftTypes.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="category-element-secondaryType"
                name="secondaryType"
                data-cy="secondaryType"
                label={translate('tfbitaApp.categoryElement.secondaryType')}
                type="select"
              >
                <option value="" key="0" />
                {draftTypes
                  ? draftTypes.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="category-element-division"
                name="division"
                data-cy="division"
                label={translate('tfbitaApp.categoryElement.division')}
                type="select"
              >
                <option value="" key="0" />
                {draftTypes
                  ? draftTypes.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="category-element-productDimension"
                name="productDimension"
                data-cy="productDimension"
                label={translate('tfbitaApp.categoryElement.productDimension')}
                type="select"
              >
                <option value="" key="0" />
                {draftReceipts
                  ? draftReceipts.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="category-element-stateOfDocuments"
                name="stateOfDocuments"
                data-cy="stateOfDocuments"
                label={translate('tfbitaApp.categoryElement.stateOfDocuments')}
                type="select"
              >
                <option value="" key="0" />
                {draftReceipts
                  ? draftReceipts.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="category-element-currencyProvisionFileType"
                name="currencyProvisionFileType"
                data-cy="currencyProvisionFileType"
                label={translate('tfbitaApp.categoryElement.currencyProvisionFileType')}
                type="select"
              >
                <option value="" key="0" />
                {draftReceipts
                  ? draftReceipts.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="category-element-statusDraft"
                name="statusDraft"
                data-cy="statusDraft"
                label={translate('tfbitaApp.categoryElement.statusDraft')}
                type="select"
              >
                <option value="" key="0" />
                {draftStatusInfos
                  ? draftStatusInfos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="category-element-categoryElement"
                name="categoryElement"
                data-cy="categoryElement"
                label={translate('tfbitaApp.categoryElement.categoryElement')}
                type="select"
              >
                <option value="" key="0" />
                {categories
                  ? categories.map(otherEntity => (
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
