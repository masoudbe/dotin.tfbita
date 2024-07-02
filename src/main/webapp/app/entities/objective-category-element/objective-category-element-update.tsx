import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IObjectiveCategory } from 'app/shared/model/objective-category.model';
import { getEntities as getObjectiveCategories } from 'app/entities/objective-category/objective-category.reducer';
import { IObjectiveCategoryElement } from 'app/shared/model/objective-category-element.model';
import { getEntity, updateEntity, createEntity, reset } from './objective-category-element.reducer';

export const ObjectiveCategoryElementUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const objectiveCategories = useAppSelector(state => state.objectiveCategory.entities);
  const objectiveCategoryElementEntity = useAppSelector(state => state.objectiveCategoryElement.entity);
  const loading = useAppSelector(state => state.objectiveCategoryElement.loading);
  const updating = useAppSelector(state => state.objectiveCategoryElement.updating);
  const updateSuccess = useAppSelector(state => state.objectiveCategoryElement.updateSuccess);

  const handleClose = () => {
    navigate('/objective-category-element');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getObjectiveCategories({}));
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
    if (values.entityId !== undefined && typeof values.entityId !== 'number') {
      values.entityId = Number(values.entityId);
    }

    const entity = {
      ...objectiveCategoryElementEntity,
      ...values,
      objectiveCategory: objectiveCategories.find(it => it.id.toString() === values.objectiveCategory?.toString()),
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
          ...objectiveCategoryElementEntity,
          objectiveCategory: objectiveCategoryElementEntity?.objectiveCategory?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.objectiveCategoryElement.home.createOrEditLabel" data-cy="ObjectiveCategoryElementCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.objectiveCategoryElement.home.createOrEditLabel">
              Create or edit a ObjectiveCategoryElement
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
                  id="objective-category-element-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.objectiveCategoryElement.entityClass')}
                id="objective-category-element-entityClass"
                name="entityClass"
                data-cy="entityClass"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.objectiveCategoryElement.entityId')}
                id="objective-category-element-entityId"
                name="entityId"
                data-cy="entityId"
                type="text"
              />
              <ValidatedField
                id="objective-category-element-objectiveCategory"
                name="objectiveCategory"
                data-cy="objectiveCategory"
                label={translate('tfbitaApp.objectiveCategoryElement.objectiveCategory')}
                type="select"
              >
                <option value="" key="0" />
                {objectiveCategories
                  ? objectiveCategories.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/objective-category-element" replace color="info">
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

export default ObjectiveCategoryElementUpdate;
