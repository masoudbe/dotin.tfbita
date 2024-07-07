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
import { IAttributeValueGroup } from 'app/shared/model/attribute-value-group.model';
import { getEntity, updateEntity, createEntity, reset } from './attribute-value-group.reducer';

export const AttributeValueGroupUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const attributes = useAppSelector(state => state.attribute.entities);
  const attributeValueGroupEntity = useAppSelector(state => state.attributeValueGroup.entity);
  const loading = useAppSelector(state => state.attributeValueGroup.loading);
  const updating = useAppSelector(state => state.attributeValueGroup.updating);
  const updateSuccess = useAppSelector(state => state.attributeValueGroup.updateSuccess);

  const handleClose = () => {
    navigate('/attribute-value-group');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getAttributes({}));
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
      ...attributeValueGroupEntity,
      ...values,
      attribute: attributes.find(it => it.id.toString() === values.attribute?.toString()),
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
          ...attributeValueGroupEntity,
          attribute: attributeValueGroupEntity?.attribute?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.attributeValueGroup.home.createOrEditLabel" data-cy="AttributeValueGroupCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.attributeValueGroup.home.createOrEditLabel">Create or edit a AttributeValueGroup</Translate>
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
                  id="attribute-value-group-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.attributeValueGroup.mandatory')}
                id="attribute-value-group-mandatory"
                name="mandatory"
                data-cy="mandatory"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.attributeValueGroup.name')}
                id="attribute-value-group-name"
                name="name"
                data-cy="name"
                type="text"
              />
              <ValidatedField
                id="attribute-value-group-attribute"
                name="attribute"
                data-cy="attribute"
                label={translate('tfbitaApp.attributeValueGroup.attribute')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/attribute-value-group" replace color="info">
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

export default AttributeValueGroupUpdate;
