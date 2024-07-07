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
import { IOrderRegServ } from 'app/shared/model/order-reg-serv.model';
import { getEntity, updateEntity, createEntity, reset } from './order-reg-serv.reducer';

export const OrderRegServUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const orderRegistrationInfos = useAppSelector(state => state.orderRegistrationInfo.entities);
  const orderRegServEntity = useAppSelector(state => state.orderRegServ.entity);
  const loading = useAppSelector(state => state.orderRegServ.loading);
  const updating = useAppSelector(state => state.orderRegServ.updating);
  const updateSuccess = useAppSelector(state => state.orderRegServ.updateSuccess);

  const handleClose = () => {
    navigate('/order-reg-serv');
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
    if (values.currencyAmount !== undefined && typeof values.currencyAmount !== 'number') {
      values.currencyAmount = Number(values.currencyAmount);
    }

    const entity = {
      ...orderRegServEntity,
      ...values,
      orderRegService: orderRegistrationInfos.find(it => it.id.toString() === values.orderRegService?.toString()),
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
          ...orderRegServEntity,
          orderRegService: orderRegServEntity?.orderRegService?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.orderRegServ.home.createOrEditLabel" data-cy="OrderRegServCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.orderRegServ.home.createOrEditLabel">Create or edit a OrderRegServ</Translate>
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
                  id="order-reg-serv-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.orderRegServ.amount')}
                id="order-reg-serv-amount"
                name="amount"
                data-cy="amount"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegServ.currencyAmount')}
                id="order-reg-serv-currencyAmount"
                name="currencyAmount"
                data-cy="currencyAmount"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegServ.unit')}
                id="order-reg-serv-unit"
                name="unit"
                data-cy="unit"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegServ.title')}
                id="order-reg-serv-title"
                name="title"
                data-cy="title"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegServ.code')}
                id="order-reg-serv-code"
                name="code"
                data-cy="code"
                type="text"
              />
              <ValidatedField
                id="order-reg-serv-orderRegService"
                name="orderRegService"
                data-cy="orderRegService"
                label={translate('tfbitaApp.orderRegServ.orderRegService')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/order-reg-serv" replace color="info">
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

export default OrderRegServUpdate;
