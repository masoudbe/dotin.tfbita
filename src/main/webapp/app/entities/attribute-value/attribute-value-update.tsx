import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ITypeAttribute } from 'app/shared/model/type-attribute.model';
import { getEntities as getTypeAttributes } from 'app/entities/type-attribute/type-attribute.reducer';
import { IAttributeValueGroup } from 'app/shared/model/attribute-value-group.model';
import { getEntities as getAttributeValueGroups } from 'app/entities/attribute-value-group/attribute-value-group.reducer';
import { IProduct } from 'app/shared/model/product.model';
import { getEntities as getProducts } from 'app/entities/product/product.reducer';
import { IAttributeValue } from 'app/shared/model/attribute-value.model';
import { getEntity, updateEntity, createEntity, reset } from './attribute-value.reducer';

export const AttributeValueUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const typeAttributes = useAppSelector(state => state.typeAttribute.entities);
  const attributeValueGroups = useAppSelector(state => state.attributeValueGroup.entities);
  const products = useAppSelector(state => state.product.entities);
  const attributeValueEntity = useAppSelector(state => state.attributeValue.entity);
  const loading = useAppSelector(state => state.attributeValue.loading);
  const updating = useAppSelector(state => state.attributeValue.updating);
  const updateSuccess = useAppSelector(state => state.attributeValue.updateSuccess);

  const handleClose = () => {
    navigate('/attribute-value');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getTypeAttributes({}));
    dispatch(getAttributeValueGroups({}));
    dispatch(getProducts({}));
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
      ...attributeValueEntity,
      ...values,
      typeAttribute: typeAttributes.find(it => it.id.toString() === values.typeAttribute?.toString()),
      attributeValueGroup: attributeValueGroups.find(it => it.id.toString() === values.attributeValueGroup?.toString()),
      product: products.find(it => it.id.toString() === values.product?.toString()),
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
          ...attributeValueEntity,
          typeAttribute: attributeValueEntity?.typeAttribute?.id,
          attributeValueGroup: attributeValueEntity?.attributeValueGroup?.id,
          product: attributeValueEntity?.product?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.attributeValue.home.createOrEditLabel" data-cy="AttributeValueCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.attributeValue.home.createOrEditLabel">Create or edit a AttributeValue</Translate>
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
                  id="attribute-value-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.attributeValue.val')}
                id="attribute-value-val"
                name="val"
                data-cy="val"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.attributeValue.customValue')}
                id="attribute-value-customValue"
                name="customValue"
                data-cy="customValue"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.attributeValue.attributeValueGroupName')}
                id="attribute-value-attributeValueGroupName"
                name="attributeValueGroupName"
                data-cy="attributeValueGroupName"
                type="text"
              />
              <ValidatedField
                id="attribute-value-typeAttribute"
                name="typeAttribute"
                data-cy="typeAttribute"
                label={translate('tfbitaApp.attributeValue.typeAttribute')}
                type="select"
              >
                <option value="" key="0" />
                {typeAttributes
                  ? typeAttributes.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="attribute-value-attributeValueGroup"
                name="attributeValueGroup"
                data-cy="attributeValueGroup"
                label={translate('tfbitaApp.attributeValue.attributeValueGroup')}
                type="select"
              >
                <option value="" key="0" />
                {attributeValueGroups
                  ? attributeValueGroups.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="attribute-value-product"
                name="product"
                data-cy="product"
                label={translate('tfbitaApp.attributeValue.product')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/attribute-value" replace color="info">
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

export default AttributeValueUpdate;
