import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ISwiftBic } from 'app/shared/model/swift-bic.model';
import { getEntity, updateEntity, createEntity, reset } from './swift-bic.reducer';

export const SwiftBicUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const swiftBicEntity = useAppSelector(state => state.swiftBic.entity);
  const loading = useAppSelector(state => state.swiftBic.loading);
  const updating = useAppSelector(state => state.swiftBic.updating);
  const updateSuccess = useAppSelector(state => state.swiftBic.updateSuccess);

  const handleClose = () => {
    navigate('/swift-bic');
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
      ...swiftBicEntity,
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
          ...swiftBicEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.swiftBic.home.createOrEditLabel" data-cy="SwiftBicCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.swiftBic.home.createOrEditLabel">Create or edit a SwiftBic</Translate>
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
                  id="swift-bic-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.swiftBic.address')}
                id="swift-bic-address"
                name="address"
                data-cy="address"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.swiftBic.address2')}
                id="swift-bic-address2"
                name="address2"
                data-cy="address2"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.swiftBic.address3')}
                id="swift-bic-address3"
                name="address3"
                data-cy="address3"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.swiftBic.address4')}
                id="swift-bic-address4"
                name="address4"
                data-cy="address4"
                type="text"
              />
              <ValidatedField label={translate('tfbitaApp.swiftBic.bank')} id="swift-bic-bank" name="bank" data-cy="bank" type="text" />
              <ValidatedField
                label={translate('tfbitaApp.swiftBic.bankName')}
                id="swift-bic-bankName"
                name="bankName"
                data-cy="bankName"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.swiftBic.bankName2')}
                id="swift-bic-bankName2"
                name="bankName2"
                data-cy="bankName2"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.swiftBic.bankName3')}
                id="swift-bic-bankName3"
                name="bankName3"
                data-cy="bankName3"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.swiftBic.branch')}
                id="swift-bic-branch"
                name="branch"
                data-cy="branch"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.swiftBic.branchName')}
                id="swift-bic-branchName"
                name="branchName"
                data-cy="branchName"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.swiftBic.branchName2')}
                id="swift-bic-branchName2"
                name="branchName2"
                data-cy="branchName2"
                type="text"
              />
              <ValidatedField label={translate('tfbitaApp.swiftBic.city')} id="swift-bic-city" name="city" data-cy="city" type="text" />
              <ValidatedField
                label={translate('tfbitaApp.swiftBic.country')}
                id="swift-bic-country"
                name="country"
                data-cy="country"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.swiftBic.location')}
                id="swift-bic-location"
                name="location"
                data-cy="location"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.swiftBic.subTypeIndicator')}
                id="swift-bic-subTypeIndicator"
                name="subTypeIndicator"
                data-cy="subTypeIndicator"
                type="text"
              />
              <ValidatedField label={translate('tfbitaApp.swiftBic.zip')} id="swift-bic-zip" name="zip" data-cy="zip" type="text" />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/swift-bic" replace color="info">
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

export default SwiftBicUpdate;
