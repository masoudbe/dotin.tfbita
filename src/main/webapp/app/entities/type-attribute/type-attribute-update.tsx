import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IAttribute } from 'app/shared/model/attribute.model';
import { getEntities as getAttributes } from 'app/entities/attribute/attribute.reducer';
import { IProductType } from 'app/shared/model/product-type.model';
import { getEntities as getProductTypes } from 'app/entities/product-type/product-type.reducer';
import { ITypeAttribute } from 'app/shared/model/type-attribute.model';
import { getEntity, updateEntity, createEntity, reset } from './type-attribute.reducer';

export const TypeAttributeUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const attributes = useAppSelector(state => state.attribute.entities);
  const productTypes = useAppSelector(state => state.productType.entities);
  const typeAttributeEntity = useAppSelector(state => state.typeAttribute.entity);
  const loading = useAppSelector(state => state.typeAttribute.loading);
  const updating = useAppSelector(state => state.typeAttribute.updating);
  const updateSuccess = useAppSelector(state => state.typeAttribute.updateSuccess);

  const handleClose = () => {
    navigate('/type-attribute');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getAttributes({}));
    dispatch(getProductTypes({}));
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
      ...typeAttributeEntity,
      ...values,
      attribute: attributes.find(it => it.id.toString() === values.attribute?.toString()),
      productTypes: mapIdList(values.productTypes),
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
          ...typeAttributeEntity,
          attribute: typeAttributeEntity?.attribute?.id,
          productTypes: typeAttributeEntity?.productTypes?.map(e => e.id.toString()),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.typeAttribute.home.createOrEditLabel" data-cy="TypeAttributeCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.typeAttribute.home.createOrEditLabel">Create or edit a TypeAttribute</Translate>
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
                  id="type-attribute-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.typeAttribute.necessary')}
                id="type-attribute-necessary"
                name="necessary"
                data-cy="necessary"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.typeAttribute.isUnique')}
                id="type-attribute-isUnique"
                name="isUnique"
                data-cy="isUnique"
                check
                type="checkbox"
              />
              <ValidatedField
                id="type-attribute-attribute"
                name="attribute"
                data-cy="attribute"
                label={translate('tfbitaApp.typeAttribute.attribute')}
                type="select"
              >
                <option value="" key="0" />
                {attributes
                  ? attributes.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                label={translate('tfbitaApp.typeAttribute.productType')}
                id="type-attribute-productType"
                data-cy="productType"
                type="select"
                multiple
                name="productTypes"
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/type-attribute" replace color="info">
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

export default TypeAttributeUpdate;
