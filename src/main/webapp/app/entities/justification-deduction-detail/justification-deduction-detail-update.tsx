import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ICategoryElement } from 'app/shared/model/category-element.model';
import { getEntities as getCategoryElements } from 'app/entities/category-element/category-element.reducer';
import { IJustificationDeductionAmount } from 'app/shared/model/justification-deduction-amount.model';
import { getEntities as getJustificationDeductionAmounts } from 'app/entities/justification-deduction-amount/justification-deduction-amount.reducer';
import { IJustificationDeductionDetail } from 'app/shared/model/justification-deduction-detail.model';
import { getEntity, updateEntity, createEntity, reset } from './justification-deduction-detail.reducer';

export const JustificationDeductionDetailUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const categoryElements = useAppSelector(state => state.categoryElement.entities);
  const justificationDeductionAmounts = useAppSelector(state => state.justificationDeductionAmount.entities);
  const justificationDeductionDetailEntity = useAppSelector(state => state.justificationDeductionDetail.entity);
  const loading = useAppSelector(state => state.justificationDeductionDetail.loading);
  const updating = useAppSelector(state => state.justificationDeductionDetail.updating);
  const updateSuccess = useAppSelector(state => state.justificationDeductionDetail.updateSuccess);

  const handleClose = () => {
    navigate('/justification-deduction-detail');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getCategoryElements({}));
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
    if (values.deductionAmount !== undefined && typeof values.deductionAmount !== 'number') {
      values.deductionAmount = Number(values.deductionAmount);
    }
    if (values.equivalentDeductionAmount !== undefined && typeof values.equivalentDeductionAmount !== 'number') {
      values.equivalentDeductionAmount = Number(values.equivalentDeductionAmount);
    }

    const entity = {
      ...justificationDeductionDetailEntity,
      ...values,
      deductionReason: categoryElements.find(it => it.id.toString() === values.deductionReason?.toString()),
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
          ...justificationDeductionDetailEntity,
          deductionReason: justificationDeductionDetailEntity?.deductionReason?.id,
          justificationDeductionAmount: justificationDeductionDetailEntity?.justificationDeductionAmount?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.justificationDeductionDetail.home.createOrEditLabel" data-cy="JustificationDeductionDetailCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.justificationDeductionDetail.home.createOrEditLabel">
              Create or edit a JustificationDeductionDetail
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
                  id="justification-deduction-detail-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.justificationDeductionDetail.deductionAmount')}
                id="justification-deduction-detail-deductionAmount"
                name="deductionAmount"
                data-cy="deductionAmount"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.justificationDeductionDetail.equivalentDeductionAmount')}
                id="justification-deduction-detail-equivalentDeductionAmount"
                name="equivalentDeductionAmount"
                data-cy="equivalentDeductionAmount"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.justificationDeductionDetail.receiveCurrencyCode')}
                id="justification-deduction-detail-receiveCurrencyCode"
                name="receiveCurrencyCode"
                data-cy="receiveCurrencyCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.justificationDeductionDetail.comment')}
                id="justification-deduction-detail-comment"
                name="comment"
                data-cy="comment"
                type="text"
              />
              <ValidatedField
                id="justification-deduction-detail-deductionReason"
                name="deductionReason"
                data-cy="deductionReason"
                label={translate('tfbitaApp.justificationDeductionDetail.deductionReason')}
                type="select"
              >
                <option value="" key="0" />
                {categoryElements
                  ? categoryElements.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="justification-deduction-detail-justificationDeductionAmount"
                name="justificationDeductionAmount"
                data-cy="justificationDeductionAmount"
                label={translate('tfbitaApp.justificationDeductionDetail.justificationDeductionAmount')}
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
                to="/justification-deduction-detail"
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

export default JustificationDeductionDetailUpdate;
