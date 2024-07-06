import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ICustomJustification } from 'app/shared/model/custom-justification.model';
import { getEntities as getCustomJustifications } from 'app/entities/custom-justification/custom-justification.reducer';
import { ICustomJustificationChild } from 'app/shared/model/custom-justification-child.model';
import { getEntity, updateEntity, createEntity, reset } from './custom-justification-child.reducer';

export const CustomJustificationChildUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const customJustifications = useAppSelector(state => state.customJustification.entities);
  const customJustificationChildEntity = useAppSelector(state => state.customJustificationChild.entity);
  const loading = useAppSelector(state => state.customJustificationChild.loading);
  const updating = useAppSelector(state => state.customJustificationChild.updating);
  const updateSuccess = useAppSelector(state => state.customJustificationChild.updateSuccess);

  const handleClose = () => {
    navigate('/custom-justification-child');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

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
    if (values.productName !== undefined && typeof values.productName !== 'number') {
      values.productName = Number(values.productName);
    }
    if (values.productId !== undefined && typeof values.productId !== 'number') {
      values.productId = Number(values.productId);
    }
    if (values.boxCount !== undefined && typeof values.boxCount !== 'number') {
      values.boxCount = Number(values.boxCount);
    }
    if (values.pureWeight !== undefined && typeof values.pureWeight !== 'number') {
      values.pureWeight = Number(values.pureWeight);
    }
    if (values.impureWeight !== undefined && typeof values.impureWeight !== 'number') {
      values.impureWeight = Number(values.impureWeight);
    }
    if (values.amountCurrency !== undefined && typeof values.amountCurrency !== 'number') {
      values.amountCurrency = Number(values.amountCurrency);
    }

    const entity = {
      ...customJustificationChildEntity,
      ...values,
      customJustification: customJustifications.find(it => it.id.toString() === values.customJustification?.toString()),
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
          ...customJustificationChildEntity,
          customJustification: customJustificationChildEntity?.customJustification?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.customJustificationChild.home.createOrEditLabel" data-cy="CustomJustificationChildCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.customJustificationChild.home.createOrEditLabel">
              Create or edit a CustomJustificationChild
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
                  id="custom-justification-child-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.customJustificationChild.item')}
                id="custom-justification-child-item"
                name="item"
                data-cy="item"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustificationChild.tariffCode')}
                id="custom-justification-child-tariffCode"
                name="tariffCode"
                data-cy="tariffCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustificationChild.productName')}
                id="custom-justification-child-productName"
                name="productName"
                data-cy="productName"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustificationChild.productId')}
                id="custom-justification-child-productId"
                name="productId"
                data-cy="productId"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustificationChild.boxCount')}
                id="custom-justification-child-boxCount"
                name="boxCount"
                data-cy="boxCount"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustificationChild.boxType')}
                id="custom-justification-child-boxType"
                name="boxType"
                data-cy="boxType"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustificationChild.pureWeight')}
                id="custom-justification-child-pureWeight"
                name="pureWeight"
                data-cy="pureWeight"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustificationChild.impureWeight')}
                id="custom-justification-child-impureWeight"
                name="impureWeight"
                data-cy="impureWeight"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustificationChild.amountCurrency')}
                id="custom-justification-child-amountCurrency"
                name="amountCurrency"
                data-cy="amountCurrency"
                type="text"
              />
              <ValidatedField
                id="custom-justification-child-customJustification"
                name="customJustification"
                data-cy="customJustification"
                label={translate('tfbitaApp.customJustificationChild.customJustification')}
                type="select"
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/custom-justification-child" replace color="info">
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

export default CustomJustificationChildUpdate;
