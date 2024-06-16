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
import { getEntity, updateEntity, createEntity, reset } from './purchase-from-other-resources.reducer';

export const PurchaseFromOtherResourcesUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const orderRegistrationInfos = useAppSelector(state => state.orderRegistrationInfo.entities);
  const purchaseFromOtherResourcesEntity = useAppSelector(state => state.purchaseFromOtherResources.entity);
  const loading = useAppSelector(state => state.purchaseFromOtherResources.loading);
  const updating = useAppSelector(state => state.purchaseFromOtherResources.updating);
  const updateSuccess = useAppSelector(state => state.purchaseFromOtherResources.updateSuccess);

  const handleClose = () => {
    navigate('/purchase-from-other-resources');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getOrderRegistrationInfos({}));
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
    if (values.amount !== undefined && typeof values.amount !== 'number') {
      values.amount = Number(values.amount);
    }
    if (values.purchaseRate !== undefined && typeof values.purchaseRate !== 'number') {
      values.purchaseRate = Number(values.purchaseRate);
    }
    if (values.orderRegistrationAmount !== undefined && typeof values.orderRegistrationAmount !== 'number') {
      values.orderRegistrationAmount = Number(values.orderRegistrationAmount);
    }

    const entity = {
      ...purchaseFromOtherResourcesEntity,
      ...values,
      purchaseFromOtherResources: orderRegistrationInfos.find(it => it.id.toString() === values.purchaseFromOtherResources?.toString()),
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
          ...purchaseFromOtherResourcesEntity,
          purchaseFromOtherResources: purchaseFromOtherResourcesEntity?.purchaseFromOtherResources?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.purchaseFromOtherResources.home.createOrEditLabel" data-cy="PurchaseFromOtherResourcesCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.purchaseFromOtherResources.home.createOrEditLabel">
              Create or edit a PurchaseFromOtherResources
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
                  id="purchase-from-other-resources-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.purchaseFromOtherResources.evidenceCode')}
                id="purchase-from-other-resources-evidenceCode"
                name="evidenceCode"
                data-cy="evidenceCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.purchaseFromOtherResources.currencySupplierDescription')}
                id="purchase-from-other-resources-currencySupplierDescription"
                name="currencySupplierDescription"
                data-cy="currencySupplierDescription"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.purchaseFromOtherResources.amount')}
                id="purchase-from-other-resources-amount"
                name="amount"
                data-cy="amount"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.purchaseFromOtherResources.purchaseRate')}
                id="purchase-from-other-resources-purchaseRate"
                name="purchaseRate"
                data-cy="purchaseRate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.purchaseFromOtherResources.orderRegistrationAmount')}
                id="purchase-from-other-resources-orderRegistrationAmount"
                name="orderRegistrationAmount"
                data-cy="orderRegistrationAmount"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.purchaseFromOtherResources.requestDate')}
                id="purchase-from-other-resources-requestDate"
                name="requestDate"
                data-cy="requestDate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.purchaseFromOtherResources.confirmationDate')}
                id="purchase-from-other-resources-confirmationDate"
                name="confirmationDate"
                data-cy="confirmationDate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.purchaseFromOtherResources.description')}
                id="purchase-from-other-resources-description"
                name="description"
                data-cy="description"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.purchaseFromOtherResources.purchaseNumber')}
                id="purchase-from-other-resources-purchaseNumber"
                name="purchaseNumber"
                data-cy="purchaseNumber"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.purchaseFromOtherResources.purchaseCurrencyName')}
                id="purchase-from-other-resources-purchaseCurrencyName"
                name="purchaseCurrencyName"
                data-cy="purchaseCurrencyName"
                type="text"
              />
              <ValidatedField
                id="purchase-from-other-resources-purchaseFromOtherResources"
                name="purchaseFromOtherResources"
                data-cy="purchaseFromOtherResources"
                label={translate('tfbitaApp.purchaseFromOtherResources.purchaseFromOtherResources')}
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
              <Button
                tag={Link}
                id="cancel-save"
                data-cy="entityCreateCancelButton"
                to="/purchase-from-other-resources"
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

export default PurchaseFromOtherResourcesUpdate;
