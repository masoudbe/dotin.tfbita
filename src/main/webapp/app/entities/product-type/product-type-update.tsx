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
import { IProductType } from 'app/shared/model/product-type.model';
import { getEntity, updateEntity, createEntity, reset } from './product-type.reducer';

export const ProductTypeUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const typeAttributes = useAppSelector(state => state.typeAttribute.entities);
  const productTypeEntity = useAppSelector(state => state.productType.entity);
  const loading = useAppSelector(state => state.productType.loading);
  const updating = useAppSelector(state => state.productType.updating);
  const updateSuccess = useAppSelector(state => state.productType.updateSuccess);

  const handleClose = () => {
    navigate('/product-type');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getTypeAttributes({}));
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
      ...productTypeEntity,
      ...values,
      productTypeAttributes: mapIdList(values.productTypeAttributes),
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
          ...productTypeEntity,
          productTypeAttributes: productTypeEntity?.productTypeAttributes?.map(e => e.id.toString()),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.productType.home.createOrEditLabel" data-cy="ProductTypeCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.productType.home.createOrEditLabel">Create or edit a ProductType</Translate>
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
                  id="product-type-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.productType.description')}
                id="product-type-description"
                name="description"
                data-cy="description"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.productType.modificationDate')}
                id="product-type-modificationDate"
                name="modificationDate"
                data-cy="modificationDate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.productType.topicCode')}
                id="product-type-topicCode"
                name="topicCode"
                data-cy="topicCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.productType.productTypeAttributes')}
                id="product-type-productTypeAttributes"
                data-cy="productTypeAttributes"
                type="select"
                multiple
                name="productTypeAttributes"
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/product-type" replace color="info">
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

export default ProductTypeUpdate;
