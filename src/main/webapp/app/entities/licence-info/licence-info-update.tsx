import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IProduct } from 'app/shared/model/product.model';
import { getEntities as getProducts } from 'app/entities/product/product.reducer';
import { IOrderRegServ } from 'app/shared/model/order-reg-serv.model';
import { getEntities as getOrderRegServs } from 'app/entities/order-reg-serv/order-reg-serv.reducer';
import { IOrderRegistrationInfo } from 'app/shared/model/order-registration-info.model';
import { getEntities as getOrderRegistrationInfos } from 'app/entities/order-registration-info/order-registration-info.reducer';
import { ILicenceInfo } from 'app/shared/model/licence-info.model';
import { getEntity, updateEntity, createEntity, reset } from './licence-info.reducer';

export const LicenceInfoUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const products = useAppSelector(state => state.product.entities);
  const orderRegServs = useAppSelector(state => state.orderRegServ.entities);
  const orderRegistrationInfos = useAppSelector(state => state.orderRegistrationInfo.entities);
  const licenceInfoEntity = useAppSelector(state => state.licenceInfo.entity);
  const loading = useAppSelector(state => state.licenceInfo.loading);
  const updating = useAppSelector(state => state.licenceInfo.updating);
  const updateSuccess = useAppSelector(state => state.licenceInfo.updateSuccess);

  const handleClose = () => {
    navigate('/licence-info');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getProducts({}));
    dispatch(getOrderRegServs({}));
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

    const entity = {
      ...licenceInfoEntity,
      ...values,
      product: products.find(it => it.id.toString() === values.product?.toString()),
      orderRegServ: orderRegServs.find(it => it.id.toString() === values.orderRegServ?.toString()),
      licenceInfo: orderRegistrationInfos.find(it => it.id.toString() === values.licenceInfo?.toString()),
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
          ...licenceInfoEntity,
          product: licenceInfoEntity?.product?.id,
          orderRegServ: licenceInfoEntity?.orderRegServ?.id,
          licenceInfo: licenceInfoEntity?.licenceInfo?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.licenceInfo.home.createOrEditLabel" data-cy="LicenceInfoCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.licenceInfo.home.createOrEditLabel">Create or edit a LicenceInfo</Translate>
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
                  id="licence-info-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.licenceInfo.organizationLicence')}
                id="licence-info-organizationLicence"
                name="organizationLicence"
                data-cy="organizationLicence"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.licenceInfo.licenceNumber')}
                id="licence-info-licenceNumber"
                name="licenceNumber"
                data-cy="licenceNumber"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.licenceInfo.licenceDate')}
                id="licence-info-licenceDate"
                name="licenceDate"
                data-cy="licenceDate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.licenceInfo.havingProduct')}
                id="licence-info-havingProduct"
                name="havingProduct"
                data-cy="havingProduct"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.licenceInfo.havingService')}
                id="licence-info-havingService"
                name="havingService"
                data-cy="havingService"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.licenceInfo.creditDate')}
                id="licence-info-creditDate"
                name="creditDate"
                data-cy="creditDate"
                type="text"
              />
              <ValidatedField
                id="licence-info-product"
                name="product"
                data-cy="product"
                label={translate('tfbitaApp.licenceInfo.product')}
                type="select"
              >
                <option value="" key="0" />
                {products
                  ? products.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="licence-info-orderRegServ"
                name="orderRegServ"
                data-cy="orderRegServ"
                label={translate('tfbitaApp.licenceInfo.orderRegServ')}
                type="select"
              >
                <option value="" key="0" />
                {orderRegServs
                  ? orderRegServs.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="licence-info-licenceInfo"
                name="licenceInfo"
                data-cy="licenceInfo"
                label={translate('tfbitaApp.licenceInfo.licenceInfo')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/licence-info" replace color="info">
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

export default LicenceInfoUpdate;
