import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IDraftTypeTopicInfo } from 'app/shared/model/draft-type-topic-info.model';
import { getEntity, updateEntity, createEntity, reset } from './draft-type-topic-info.reducer';

export const DraftTypeTopicInfoUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const draftTypeTopicInfoEntity = useAppSelector(state => state.draftTypeTopicInfo.entity);
  const loading = useAppSelector(state => state.draftTypeTopicInfo.loading);
  const updating = useAppSelector(state => state.draftTypeTopicInfo.updating);
  const updateSuccess = useAppSelector(state => state.draftTypeTopicInfo.updateSuccess);

  const handleClose = () => {
    navigate('/draft-type-topic-info');
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
    if (values.currencySellCommissionTopic !== undefined && typeof values.currencySellCommissionTopic !== 'number') {
      values.currencySellCommissionTopic = Number(values.currencySellCommissionTopic);
    }
    if (values.documentReceiptDisciplinaryTopic !== undefined && typeof values.documentReceiptDisciplinaryTopic !== 'number') {
      values.documentReceiptDisciplinaryTopic = Number(values.documentReceiptDisciplinaryTopic);
    }
    if (values.draftMainTopic !== undefined && typeof values.draftMainTopic !== 'number') {
      values.draftMainTopic = Number(values.draftMainTopic);
    }
    if (values.insuranceCostTopic !== undefined && typeof values.insuranceCostTopic !== 'number') {
      values.insuranceCostTopic = Number(values.insuranceCostTopic);
    }
    if (values.justificationDisciplinaryTopic !== undefined && typeof values.justificationDisciplinaryTopic !== 'number') {
      values.justificationDisciplinaryTopic = Number(values.justificationDisciplinaryTopic);
    }
    if (values.openDraftDisciplinaryTopic !== undefined && typeof values.openDraftDisciplinaryTopic !== 'number') {
      values.openDraftDisciplinaryTopic = Number(values.openDraftDisciplinaryTopic);
    }
    if (values.otherCostsTopic !== undefined && typeof values.otherCostsTopic !== 'number') {
      values.otherCostsTopic = Number(values.otherCostsTopic);
    }
    if (values.postTelegraphSwiftCostsTopic !== undefined && typeof values.postTelegraphSwiftCostsTopic !== 'number') {
      values.postTelegraphSwiftCostsTopic = Number(values.postTelegraphSwiftCostsTopic);
    }

    const entity = {
      ...draftTypeTopicInfoEntity,
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
          ...draftTypeTopicInfoEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.draftTypeTopicInfo.home.createOrEditLabel" data-cy="DraftTypeTopicInfoCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.draftTypeTopicInfo.home.createOrEditLabel">Create or edit a DraftTypeTopicInfo</Translate>
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
                  id="draft-type-topic-info-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.draftTypeTopicInfo.currencySellCommissionTopic')}
                id="draft-type-topic-info-currencySellCommissionTopic"
                name="currencySellCommissionTopic"
                data-cy="currencySellCommissionTopic"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftTypeTopicInfo.documentReceiptDisciplinaryTopic')}
                id="draft-type-topic-info-documentReceiptDisciplinaryTopic"
                name="documentReceiptDisciplinaryTopic"
                data-cy="documentReceiptDisciplinaryTopic"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftTypeTopicInfo.draftMainTopic')}
                id="draft-type-topic-info-draftMainTopic"
                name="draftMainTopic"
                data-cy="draftMainTopic"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftTypeTopicInfo.insuranceCostTopic')}
                id="draft-type-topic-info-insuranceCostTopic"
                name="insuranceCostTopic"
                data-cy="insuranceCostTopic"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftTypeTopicInfo.justificationDisciplinaryTopic')}
                id="draft-type-topic-info-justificationDisciplinaryTopic"
                name="justificationDisciplinaryTopic"
                data-cy="justificationDisciplinaryTopic"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftTypeTopicInfo.openDraftDisciplinaryTopic')}
                id="draft-type-topic-info-openDraftDisciplinaryTopic"
                name="openDraftDisciplinaryTopic"
                data-cy="openDraftDisciplinaryTopic"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftTypeTopicInfo.otherCostsTopic')}
                id="draft-type-topic-info-otherCostsTopic"
                name="otherCostsTopic"
                data-cy="otherCostsTopic"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftTypeTopicInfo.postTelegraphSwiftCostsTopic')}
                id="draft-type-topic-info-postTelegraphSwiftCostsTopic"
                name="postTelegraphSwiftCostsTopic"
                data-cy="postTelegraphSwiftCostsTopic"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/draft-type-topic-info" replace color="info">
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

export default DraftTypeTopicInfoUpdate;
