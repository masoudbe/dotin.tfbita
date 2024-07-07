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
import { IDraftReceipt } from 'app/shared/model/draft-receipt.model';
import { getEntities as getDraftReceipts } from 'app/entities/draft-receipt/draft-receipt.reducer';
import { IDraftProductInfo } from 'app/shared/model/draft-product-info.model';
import { getEntity, updateEntity, createEntity, reset } from './draft-product-info.reducer';

export const DraftProductInfoUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const products = useAppSelector(state => state.product.entities);
  const draftReceipts = useAppSelector(state => state.draftReceipt.entities);
  const draftProductInfoEntity = useAppSelector(state => state.draftProductInfo.entity);
  const loading = useAppSelector(state => state.draftProductInfo.loading);
  const updating = useAppSelector(state => state.draftProductInfo.updating);
  const updateSuccess = useAppSelector(state => state.draftProductInfo.updateSuccess);

  const handleClose = () => {
    navigate('/draft-product-info');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getProducts({}));
    dispatch(getDraftReceipts({}));
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
      ...draftProductInfoEntity,
      ...values,
      product: products.find(it => it.id.toString() === values.product?.toString()),
      draftReceipt: draftReceipts.find(it => it.id.toString() === values.draftReceipt?.toString()),
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
          ...draftProductInfoEntity,
          product: draftProductInfoEntity?.product?.id,
          draftReceipt: draftProductInfoEntity?.draftReceipt?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.draftProductInfo.home.createOrEditLabel" data-cy="DraftProductInfoCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.draftProductInfo.home.createOrEditLabel">Create or edit a DraftProductInfo</Translate>
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
                  id="draft-product-info-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.draftProductInfo.productAmount')}
                id="draft-product-info-productAmount"
                name="productAmount"
                data-cy="productAmount"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftProductInfo.productDimension')}
                id="draft-product-info-productDimension"
                name="productDimension"
                data-cy="productDimension"
                type="text"
              />
              <ValidatedField
                id="draft-product-info-product"
                name="product"
                data-cy="product"
                label={translate('tfbitaApp.draftProductInfo.product')}
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
                id="draft-product-info-draftReceipt"
                name="draftReceipt"
                data-cy="draftReceipt"
                label={translate('tfbitaApp.draftProductInfo.draftReceipt')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/draft-product-info" replace color="info">
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

export default DraftProductInfoUpdate;
