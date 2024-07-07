import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IJustificationDeductionAmount } from 'app/shared/model/justification-deduction-amount.model';
import { getEntities as getJustificationDeductionAmounts } from 'app/entities/justification-deduction-amount/justification-deduction-amount.reducer';
import { IJustificationDeductionAmountPart } from 'app/shared/model/justification-deduction-amount-part.model';
import { getEntity, updateEntity, createEntity, reset } from './justification-deduction-amount-part.reducer';

export const JustificationDeductionAmountPartUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const justificationDeductionAmounts = useAppSelector(state => state.justificationDeductionAmount.entities);
  const justificationDeductionAmountPartEntity = useAppSelector(state => state.justificationDeductionAmountPart.entity);
  const loading = useAppSelector(state => state.justificationDeductionAmountPart.loading);
  const updating = useAppSelector(state => state.justificationDeductionAmountPart.updating);
  const updateSuccess = useAppSelector(state => state.justificationDeductionAmountPart.updateSuccess);

  const handleClose = () => {
    navigate('/justification-deduction-amount-part');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getJustificationDeductionAmounts({}));
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
    if (values.amount !== undefined && typeof values.amount !== 'number') {
      values.amount = Number(values.amount);
    }
    if (values.amountBasedOnRial !== undefined && typeof values.amountBasedOnRial !== 'number') {
      values.amountBasedOnRial = Number(values.amountBasedOnRial);
    }
    if (values.sellRate !== undefined && typeof values.sellRate !== 'number') {
      values.sellRate = Number(values.sellRate);
    }
    if (values.buyRate !== undefined && typeof values.buyRate !== 'number') {
      values.buyRate = Number(values.buyRate);
    }

    const entity = {
      ...justificationDeductionAmountPartEntity,
      ...values,
      justificationDeductionAmount: justificationDeductionAmounts.find(
        it => it.id.toString() === values.justificationDeductionAmount?.toString(),
      ),
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
          ...justificationDeductionAmountPartEntity,
          justificationDeductionAmount: justificationDeductionAmountPartEntity?.justificationDeductionAmount?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2
            id="tfbitaApp.justificationDeductionAmountPart.home.createOrEditLabel"
            data-cy="JustificationDeductionAmountPartCreateUpdateHeading"
          >
            <Translate contentKey="tfbitaApp.justificationDeductionAmountPart.home.createOrEditLabel">
              Create or edit a JustificationDeductionAmountPart
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
                  id="justification-deduction-amount-part-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.justificationDeductionAmountPart.receiveTransactionCode')}
                id="justification-deduction-amount-part-receiveTransactionCode"
                name="receiveTransactionCode"
                data-cy="receiveTransactionCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.justificationDeductionAmountPart.returnTransactionCode')}
                id="justification-deduction-amount-part-returnTransactionCode"
                name="returnTransactionCode"
                data-cy="returnTransactionCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.justificationDeductionAmountPart.date')}
                id="justification-deduction-amount-part-date"
                name="date"
                data-cy="date"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.justificationDeductionAmountPart.amount')}
                id="justification-deduction-amount-part-amount"
                name="amount"
                data-cy="amount"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.justificationDeductionAmountPart.amountBasedOnRial')}
                id="justification-deduction-amount-part-amountBasedOnRial"
                name="amountBasedOnRial"
                data-cy="amountBasedOnRial"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.justificationDeductionAmountPart.depositNumber')}
                id="justification-deduction-amount-part-depositNumber"
                name="depositNumber"
                data-cy="depositNumber"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.justificationDeductionAmountPart.receiveCurrencyCode')}
                id="justification-deduction-amount-part-receiveCurrencyCode"
                name="receiveCurrencyCode"
                data-cy="receiveCurrencyCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.justificationDeductionAmountPart.currencyRateDate')}
                id="justification-deduction-amount-part-currencyRateDate"
                name="currencyRateDate"
                data-cy="currencyRateDate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.justificationDeductionAmountPart.sellRate')}
                id="justification-deduction-amount-part-sellRate"
                name="sellRate"
                data-cy="sellRate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.justificationDeductionAmountPart.buyRate')}
                id="justification-deduction-amount-part-buyRate"
                name="buyRate"
                data-cy="buyRate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.justificationDeductionAmountPart.comment')}
                id="justification-deduction-amount-part-comment"
                name="comment"
                data-cy="comment"
                type="text"
              />
              <ValidatedField
                id="justification-deduction-amount-part-justificationDeductionAmount"
                name="justificationDeductionAmount"
                data-cy="justificationDeductionAmount"
                label={translate('tfbitaApp.justificationDeductionAmountPart.justificationDeductionAmount')}
                type="select"
              >
                <option value="" key="0" />
                {justificationDeductionAmounts
                  ? justificationDeductionAmounts.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button
                tag={Link}
                id="cancel-save"
                data-cy="entityCreateCancelButton"
                to="/justification-deduction-amount-part"
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

export default JustificationDeductionAmountPartUpdate;
