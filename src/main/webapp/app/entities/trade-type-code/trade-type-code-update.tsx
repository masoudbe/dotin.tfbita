import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ITradeTypeCode } from 'app/shared/model/trade-type-code.model';
import { getEntity, updateEntity, createEntity, reset } from './trade-type-code.reducer';

export const TradeTypeCodeUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const tradeTypeCodeEntity = useAppSelector(state => state.tradeTypeCode.entity);
  const loading = useAppSelector(state => state.tradeTypeCode.loading);
  const updating = useAppSelector(state => state.tradeTypeCode.updating);
  const updateSuccess = useAppSelector(state => state.tradeTypeCode.updateSuccess);

  const handleClose = () => {
    navigate('/trade-type-code');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }
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
      ...tradeTypeCodeEntity,
      ...values,
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
          ...tradeTypeCodeEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.tradeTypeCode.home.createOrEditLabel" data-cy="TradeTypeCodeCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.tradeTypeCode.home.createOrEditLabel">Create or edit a TradeTypeCode</Translate>
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
                  id="trade-type-code-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.tradeTypeCode.latinName')}
                id="trade-type-code-latinName"
                name="latinName"
                data-cy="latinName"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.tradeTypeCode.name')}
                id="trade-type-code-name"
                name="name"
                data-cy="name"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/trade-type-code" replace color="info">
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

export default TradeTypeCodeUpdate;
