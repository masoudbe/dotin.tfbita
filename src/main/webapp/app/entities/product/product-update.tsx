import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IProductType } from 'app/shared/model/product-type.model';
import { getEntities as getProductTypes } from 'app/entities/product-type/product-type.reducer';
import { IOrderRegistrationInfo } from 'app/shared/model/order-registration-info.model';
import { getEntities as getOrderRegistrationInfos } from 'app/entities/order-registration-info/order-registration-info.reducer';
import { IDraft } from 'app/shared/model/draft.model';
import { getEntities as getDrafts } from 'app/entities/draft/draft.reducer';
import { ICustomJustification } from 'app/shared/model/custom-justification.model';
import { getEntities as getCustomJustifications } from 'app/entities/custom-justification/custom-justification.reducer';
import { IProduct } from 'app/shared/model/product.model';
import { getEntity, updateEntity, createEntity, reset } from './product.reducer';

export const ProductUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const productTypes = useAppSelector(state => state.productType.entities);
  const orderRegistrationInfos = useAppSelector(state => state.orderRegistrationInfo.entities);
  const drafts = useAppSelector(state => state.draft.entities);
  const customJustifications = useAppSelector(state => state.customJustification.entities);
  const productEntity = useAppSelector(state => state.product.entity);
  const loading = useAppSelector(state => state.product.loading);
  const updating = useAppSelector(state => state.product.updating);
  const updateSuccess = useAppSelector(state => state.product.updateSuccess);

  const handleClose = () => {
    navigate('/product');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getProductTypes({}));
    dispatch(getOrderRegistrationInfos({}));
    dispatch(getDrafts({}));
    dispatch(getCustomJustifications({}));
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
      ...productEntity,
      ...values,
      productType: productTypes.find(it => it.id.toString() === values.productType?.toString()),
      orderRegistrationInfos: mapIdList(values.orderRegistrationInfos),
      drafts: mapIdList(values.drafts),
      customJustifications: mapIdList(values.customJustifications),
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
          ...productEntity,
          productType: productEntity?.productType?.id,
          orderRegistrationInfos: productEntity?.orderRegistrationInfos?.map(e => e.id.toString()),
          drafts: productEntity?.drafts?.map(e => e.id.toString()),
          customJustifications: productEntity?.customJustifications?.map(e => e.id.toString()),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.product.home.createOrEditLabel" data-cy="ProductCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.product.home.createOrEditLabel">Create or edit a Product</Translate>
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
                  id="product-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField label={translate('tfbitaApp.product.code')} id="product-code" name="code" data-cy="code" type="text" />
              <ValidatedField
                label={translate('tfbitaApp.product.modificationDate')}
                id="product-modificationDate"
                name="modificationDate"
                data-cy="modificationDate"
                type="text"
              />
              <ValidatedField label={translate('tfbitaApp.product.name')} id="product-name" name="name" data-cy="name" type="text" />
              <ValidatedField
                id="product-productType"
                name="productType"
                data-cy="productType"
                label={translate('tfbitaApp.product.productType')}
                type="select"
              >
                <option value="" key="0" />
                {productTypes
                  ? productTypes.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                label={translate('tfbitaApp.product.orderRegistrationInfo')}
                id="product-orderRegistrationInfo"
                data-cy="orderRegistrationInfo"
                type="select"
                multiple
                name="orderRegistrationInfos"
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
                label={translate('tfbitaApp.product.draft')}
                id="product-draft"
                data-cy="draft"
                type="select"
                multiple
                name="drafts"
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
                label={translate('tfbitaApp.product.customJustification')}
                id="product-customJustification"
                data-cy="customJustification"
                type="select"
                multiple
                name="customJustifications"
              >
                <option value="" key="0" />
                {customJustifications
                  ? customJustifications.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/product" replace color="info">
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

export default ProductUpdate;
