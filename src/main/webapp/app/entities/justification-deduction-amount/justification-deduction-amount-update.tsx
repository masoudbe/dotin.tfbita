import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IJustificationDeductionAmount } from 'app/shared/model/justification-deduction-amount.model';
import { getEntity, updateEntity, createEntity, reset } from './justification-deduction-amount.reducer';

export const JustificationDeductionAmountUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const justificationDeductionAmountEntity = useAppSelector(state => state.justificationDeductionAmount.entity);
  const loading = useAppSelector(state => state.justificationDeductionAmount.loading);
  const updating = useAppSelector(state => state.justificationDeductionAmount.updating);
  const updateSuccess = useAppSelector(state => state.justificationDeductionAmount.updateSuccess);

  const handleClose = () => {
    navigate('/justification-deduction-amount');
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
    if (values.deductionAmount !== undefined && typeof values.deductionAmount !== 'number') {
      values.deductionAmount = Number(values.deductionAmount);
    }
    if (values.remainingDeductionAmount !== undefined && typeof values.remainingDeductionAmount !== 'number') {
      values.remainingDeductionAmount = Number(values.remainingDeductionAmount);
    }
    if (values.receivedDeductionAmount !== undefined && typeof values.receivedDeductionAmount !== 'number') {
      values.receivedDeductionAmount = Number(values.receivedDeductionAmount);
    }

    const entity = {
      ...justificationDeductionAmountEntity,
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
          ...justificationDeductionAmountEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.justificationDeductionAmount.home.createOrEditLabel" data-cy="JustificationDeductionAmountCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.justificationDeductionAmount.home.createOrEditLabel">
              Create or edit a JustificationDeductionAmount
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
                  id="justification-deduction-amount-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.justificationDeductionAmount.deductionAmount')}
                id="justification-deduction-amount-deductionAmount"
                name="deductionAmount"
                data-cy="deductionAmount"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.justificationDeductionAmount.remainingDeductionAmount')}
                id="justification-deduction-amount-remainingDeductionAmount"
                name="remainingDeductionAmount"
                data-cy="remainingDeductionAmount"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.justificationDeductionAmount.receivedDeductionAmount')}
                id="justification-deduction-amount-receivedDeductionAmount"
                name="receivedDeductionAmount"
                data-cy="receivedDeductionAmount"
                type="text"
              />
              <Button
                tag={Link}
                id="cancel-save"
                data-cy="entityCreateCancelButton"
                to="/justification-deduction-amount"
                replace
                color="info"
              >
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

export default JustificationDeductionAmountUpdate;
