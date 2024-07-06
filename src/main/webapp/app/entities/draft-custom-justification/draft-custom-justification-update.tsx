import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IDraftReceipt } from 'app/shared/model/draft-receipt.model';
import { getEntities as getDraftReceipts } from 'app/entities/draft-receipt/draft-receipt.reducer';
import { IDraft } from 'app/shared/model/draft.model';
import { getEntities as getDrafts } from 'app/entities/draft/draft.reducer';
import { IDraftCustomJustification } from 'app/shared/model/draft-custom-justification.model';
import { getEntity, updateEntity, createEntity, reset } from './draft-custom-justification.reducer';

export const DraftCustomJustificationUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const draftReceipts = useAppSelector(state => state.draftReceipt.entities);
  const drafts = useAppSelector(state => state.draft.entities);
  const draftCustomJustificationEntity = useAppSelector(state => state.draftCustomJustification.entity);
  const loading = useAppSelector(state => state.draftCustomJustification.loading);
  const updating = useAppSelector(state => state.draftCustomJustification.updating);
  const updateSuccess = useAppSelector(state => state.draftCustomJustification.updateSuccess);

  const handleClose = () => {
    navigate('/draft-custom-justification');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getDraftReceipts({}));
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
      ...draftCustomJustificationEntity,
      ...values,
      draftReceipts: mapIdList(values.draftReceipts),
      draft: drafts.find(it => it.id.toString() === values.draft?.toString()),
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
          ...draftCustomJustificationEntity,
          draftReceipts: draftCustomJustificationEntity?.draftReceipts?.map(e => e.id.toString()),
          draft: draftCustomJustificationEntity?.draft?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.draftCustomJustification.home.createOrEditLabel" data-cy="DraftCustomJustificationCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.draftCustomJustification.home.createOrEditLabel">
              Create or edit a DraftCustomJustification
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
                  id="draft-custom-justification-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.draftCustomJustification.draftReceipts')}
                id="draft-custom-justification-draftReceipts"
                data-cy="draftReceipts"
                type="select"
                multiple
                name="draftReceipts"
              >
                <option value="" key="0" />
                {draftReceipts
                  ? draftReceipts.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="draft-custom-justification-draft"
                name="draft"
                data-cy="draft"
                label={translate('tfbitaApp.draftCustomJustification.draft')}
                type="select"
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/draft-custom-justification" replace color="info">
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

export default DraftCustomJustificationUpdate;
