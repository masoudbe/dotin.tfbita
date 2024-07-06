import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ICategory } from 'app/shared/model/category.model';
import { getEntities as getCategories } from 'app/entities/category/category.reducer';
import { ICategoryElement } from 'app/shared/model/category-element.model';
import { getEntity, updateEntity, createEntity, reset } from './category-element.reducer';

export const CategoryElementUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const categories = useAppSelector(state => state.category.entities);
  const categoryElementEntity = useAppSelector(state => state.categoryElement.entity);
  const loading = useAppSelector(state => state.categoryElement.loading);
  const updating = useAppSelector(state => state.categoryElement.updating);
  const updateSuccess = useAppSelector(state => state.categoryElement.updateSuccess);

  const handleClose = () => {
    navigate('/category-element');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getCategories({}));
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
      ...categoryElementEntity,
      ...values,
      category: categories.find(it => it.id.toString() === values.category?.toString()),
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
          ...categoryElementEntity,
          category: categoryElementEntity?.category?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.categoryElement.home.createOrEditLabel" data-cy="CategoryElementCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.categoryElement.home.createOrEditLabel">Create or edit a CategoryElement</Translate>
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
                  id="category-element-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.categoryElement.val')}
                id="category-element-val"
                name="val"
                data-cy="val"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.categoryElement.categoryName')}
                id="category-element-categoryName"
                name="categoryName"
                data-cy="categoryName"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.categoryElement.code')}
                id="category-element-code"
                name="code"
                data-cy="code"
                type="text"
              />
              <ValidatedField
                id="category-element-category"
                name="category"
                data-cy="category"
                label={translate('tfbitaApp.categoryElement.category')}
                type="select"
              >
                <option value="" key="0" />
                {categories
                  ? categories.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/category-element" replace color="info">
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

export default CategoryElementUpdate;
