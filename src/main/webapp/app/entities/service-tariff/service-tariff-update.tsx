import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IDraft } from 'app/shared/model/draft.model';
import { getEntities as getDrafts } from 'app/entities/draft/draft.reducer';
import { IServiceTariff } from 'app/shared/model/service-tariff.model';
import { getEntity, updateEntity, createEntity, reset } from './service-tariff.reducer';

export const ServiceTariffUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const drafts = useAppSelector(state => state.draft.entities);
  const serviceTariffEntity = useAppSelector(state => state.serviceTariff.entity);
  const loading = useAppSelector(state => state.serviceTariff.loading);
  const updating = useAppSelector(state => state.serviceTariff.updating);
  const updateSuccess = useAppSelector(state => state.serviceTariff.updateSuccess);

  const handleClose = () => {
    navigate('/service-tariff');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getDrafts({}));
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
      ...serviceTariffEntity,
      ...values,
      drafts: mapIdList(values.drafts),
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
          ...serviceTariffEntity,
          drafts: serviceTariffEntity?.drafts?.map(e => e.id.toString()),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.serviceTariff.home.createOrEditLabel" data-cy="ServiceTariffCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.serviceTariff.home.createOrEditLabel">Create or edit a ServiceTariff</Translate>
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
                  id="service-tariff-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.serviceTariff.code')}
                id="service-tariff-code"
                name="code"
                data-cy="code"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.serviceTariff.title')}
                id="service-tariff-title"
                name="title"
                data-cy="title"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.serviceTariff.draft')}
                id="service-tariff-draft"
                data-cy="draft"
                type="select"
                multiple
                name="drafts"
              >
                <option value="" key="0" />
                {drafts
                  ? drafts.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/service-tariff" replace color="info">
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

export default ServiceTariffUpdate;
