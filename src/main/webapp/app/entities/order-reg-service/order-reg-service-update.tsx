import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IServiceTariff } from 'app/shared/model/service-tariff.model';
import { getEntities as getServiceTariffs } from 'app/entities/service-tariff/service-tariff.reducer';
import { IOrderRegistrationInfo } from 'app/shared/model/order-registration-info.model';
import { getEntities as getOrderRegistrationInfos } from 'app/entities/order-registration-info/order-registration-info.reducer';
import { IOrderRegService } from 'app/shared/model/order-reg-service.model';
import { getEntity, updateEntity, createEntity, reset } from './order-reg-service.reducer';

export const OrderRegServiceUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const serviceTariffs = useAppSelector(state => state.serviceTariff.entities);
  const orderRegistrationInfos = useAppSelector(state => state.orderRegistrationInfo.entities);
  const orderRegServiceEntity = useAppSelector(state => state.orderRegService.entity);
  const loading = useAppSelector(state => state.orderRegService.loading);
  const updating = useAppSelector(state => state.orderRegService.updating);
  const updateSuccess = useAppSelector(state => state.orderRegService.updateSuccess);

  const handleClose = () => {
    navigate('/order-reg-service');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getServiceTariffs({}));
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
    if (values.currencyAmount !== undefined && typeof values.currencyAmount !== 'number') {
      values.currencyAmount = Number(values.currencyAmount);
    }

    const entity = {
      ...orderRegServiceEntity,
      ...values,
      serviceTariff: serviceTariffs.find(it => it.id.toString() === values.serviceTariff?.toString()),
      orderRegistrationInfo: orderRegistrationInfos.find(it => it.id.toString() === values.orderRegistrationInfo?.toString()),
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
          ...orderRegServiceEntity,
          serviceTariff: orderRegServiceEntity?.serviceTariff?.id,
          orderRegistrationInfo: orderRegServiceEntity?.orderRegistrationInfo?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.orderRegService.home.createOrEditLabel" data-cy="OrderRegServiceCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.orderRegService.home.createOrEditLabel">Create or edit a OrderRegService</Translate>
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
                  id="order-reg-service-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.orderRegService.amount')}
                id="order-reg-service-amount"
                name="amount"
                data-cy="amount"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegService.currencyAmount')}
                id="order-reg-service-currencyAmount"
                name="currencyAmount"
                data-cy="currencyAmount"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegService.unit')}
                id="order-reg-service-unit"
                name="unit"
                data-cy="unit"
                type="text"
              />
              <ValidatedField
                id="order-reg-service-serviceTariff"
                name="serviceTariff"
                data-cy="serviceTariff"
                label={translate('tfbitaApp.orderRegService.serviceTariff')}
                type="select"
              >
                <option value="" key="0" />
                {serviceTariffs
                  ? serviceTariffs.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="order-reg-service-orderRegistrationInfo"
                name="orderRegistrationInfo"
                data-cy="orderRegistrationInfo"
                label={translate('tfbitaApp.orderRegService.orderRegistrationInfo')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/order-reg-service" replace color="info">
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

export default OrderRegServiceUpdate;
