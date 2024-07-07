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
import { IDraftTypeTopicInfo } from 'app/shared/model/draft-type-topic-info.model';
import { getEntities as getDraftTypeTopicInfos } from 'app/entities/draft-type-topic-info/draft-type-topic-info.reducer';
import { ICreditTypeConditionInfo } from 'app/shared/model/credit-type-condition-info.model';
import { getEntities as getCreditTypeConditionInfos } from 'app/entities/credit-type-condition-info/credit-type-condition-info.reducer';
import { IDraftTypeAccountInfo } from 'app/shared/model/draft-type-account-info.model';
import { getEntities as getDraftTypeAccountInfos } from 'app/entities/draft-type-account-info/draft-type-account-info.reducer';
import { IDraftRequestType } from 'app/shared/model/draft-request-type.model';
import { getEntities as getDraftRequestTypes } from 'app/entities/draft-request-type/draft-request-type.reducer';
import { IObjectiveCategoryElement } from 'app/shared/model/objective-category-element.model';
import { getEntities as getObjectiveCategoryElements } from 'app/entities/objective-category-element/objective-category-element.reducer';
import { IStringValue } from 'app/shared/model/string-value.model';
import { getEntities as getStringValues } from 'app/entities/string-value/string-value.reducer';
import { IDraftType } from 'app/shared/model/draft-type.model';
import { getEntity, updateEntity, createEntity, reset } from './draft-type.reducer';

export const DraftTypeUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const categoryElements = useAppSelector(state => state.categoryElement.entities);
  const draftTypeTopicInfos = useAppSelector(state => state.draftTypeTopicInfo.entities);
  const creditTypeConditionInfos = useAppSelector(state => state.creditTypeConditionInfo.entities);
  const draftTypeAccountInfos = useAppSelector(state => state.draftTypeAccountInfo.entities);
  const draftRequestTypes = useAppSelector(state => state.draftRequestType.entities);
  const objectiveCategoryElements = useAppSelector(state => state.objectiveCategoryElement.entities);
  const stringValues = useAppSelector(state => state.stringValue.entities);
  const draftTypeEntity = useAppSelector(state => state.draftType.entity);
  const loading = useAppSelector(state => state.draftType.loading);
  const updating = useAppSelector(state => state.draftType.updating);
  const updateSuccess = useAppSelector(state => state.draftType.updateSuccess);

  const handleClose = () => {
    navigate('/draft-type');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getCategoryElements({}));
    dispatch(getDraftTypeTopicInfos({}));
    dispatch(getCreditTypeConditionInfos({}));
    dispatch(getDraftTypeAccountInfos({}));
    dispatch(getDraftRequestTypes({}));
    dispatch(getObjectiveCategoryElements({}));
    dispatch(getStringValues({}));
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
    if (values.alarmTime !== undefined && typeof values.alarmTime !== 'number') {
      values.alarmTime = Number(values.alarmTime);
    }
    if (values.duration !== undefined && typeof values.duration !== 'number') {
      values.duration = Number(values.duration);
    }
    if (values.latestCreditSerial !== undefined && typeof values.latestCreditSerial !== 'number') {
      values.latestCreditSerial = Number(values.latestCreditSerial);
    }

    const entity = {
      ...draftTypeEntity,
      ...values,
      type: categoryElements.find(it => it.id.toString() === values.type?.toString()),
      secondaryType: categoryElements.find(it => it.id.toString() === values.secondaryType?.toString()),
      division: categoryElements.find(it => it.id.toString() === values.division?.toString()),
      topicInfo: draftTypeTopicInfos.find(it => it.id.toString() === values.topicInfo?.toString()),
      conditionInfo: creditTypeConditionInfos.find(it => it.id.toString() === values.conditionInfo?.toString()),
      accountInfo: draftTypeAccountInfos.find(it => it.id.toString() === values.accountInfo?.toString()),
      requestType: draftRequestTypes.find(it => it.id.toString() === values.requestType?.toString()),
      acceptableProductTypes: objectiveCategoryElements.find(it => it.id.toString() === values.acceptableProductTypes?.toString()),
      userGroups: mapIdList(values.userGroups),
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
          ...draftTypeEntity,
          type: draftTypeEntity?.type?.id,
          secondaryType: draftTypeEntity?.secondaryType?.id,
          division: draftTypeEntity?.division?.id,
          topicInfo: draftTypeEntity?.topicInfo?.id,
          conditionInfo: draftTypeEntity?.conditionInfo?.id,
          accountInfo: draftTypeEntity?.accountInfo?.id,
          requestType: draftTypeEntity?.requestType?.id,
          acceptableProductTypes: draftTypeEntity?.acceptableProductTypes?.id,
          userGroups: draftTypeEntity?.userGroups?.map(e => e.id.toString()),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.draftType.home.createOrEditLabel" data-cy="DraftTypeCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.draftType.home.createOrEditLabel">Create or edit a DraftType</Translate>
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
                  id="draft-type-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.draftType.alarmTime')}
                id="draft-type-alarmTime"
                name="alarmTime"
                data-cy="alarmTime"
                type="text"
              />
              <ValidatedField label={translate('tfbitaApp.draftType.code')} id="draft-type-code" name="code" data-cy="code" type="text" />
              <ValidatedField
                label={translate('tfbitaApp.draftType.disableDate')}
                id="draft-type-disableDate"
                name="disableDate"
                data-cy="disableDate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftType.duration')}
                id="draft-type-duration"
                name="duration"
                data-cy="duration"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftType.hasAssurance')}
                id="draft-type-hasAssurance"
                name="hasAssurance"
                data-cy="hasAssurance"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftType.hasSanction')}
                id="draft-type-hasSanction"
                name="hasSanction"
                data-cy="hasSanction"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftType.latestCreditSerial')}
                id="draft-type-latestCreditSerial"
                name="latestCreditSerial"
                data-cy="latestCreditSerial"
                type="text"
              />
              <ValidatedField label={translate('tfbitaApp.draftType.name')} id="draft-type-name" name="name" data-cy="name" type="text" />
              <ValidatedField
                label={translate('tfbitaApp.draftType.portal')}
                id="draft-type-portal"
                name="portal"
                data-cy="portal"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftType.usable')}
                id="draft-type-usable"
                name="usable"
                data-cy="usable"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftType.currenciesCodes')}
                id="draft-type-currenciesCodes"
                name="currenciesCodes"
                data-cy="currenciesCodes"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftType.defaultCurrencyCode')}
                id="draft-type-defaultCurrencyCode"
                name="defaultCurrencyCode"
                data-cy="defaultCurrencyCode"
                type="text"
              />
              <ValidatedField id="draft-type-type" name="type" data-cy="type" label={translate('tfbitaApp.draftType.type')} type="select">
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
                id="draft-type-secondaryType"
                name="secondaryType"
                data-cy="secondaryType"
                label={translate('tfbitaApp.draftType.secondaryType')}
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
                id="draft-type-division"
                name="division"
                data-cy="division"
                label={translate('tfbitaApp.draftType.division')}
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
                id="draft-type-topicInfo"
                name="topicInfo"
                data-cy="topicInfo"
                label={translate('tfbitaApp.draftType.topicInfo')}
                type="select"
              >
                <option value="" key="0" />
                {draftTypeTopicInfos
                  ? draftTypeTopicInfos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="draft-type-conditionInfo"
                name="conditionInfo"
                data-cy="conditionInfo"
                label={translate('tfbitaApp.draftType.conditionInfo')}
                type="select"
              >
                <option value="" key="0" />
                {creditTypeConditionInfos
                  ? creditTypeConditionInfos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="draft-type-accountInfo"
                name="accountInfo"
                data-cy="accountInfo"
                label={translate('tfbitaApp.draftType.accountInfo')}
                type="select"
              >
                <option value="" key="0" />
                {draftTypeAccountInfos
                  ? draftTypeAccountInfos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="draft-type-requestType"
                name="requestType"
                data-cy="requestType"
                label={translate('tfbitaApp.draftType.requestType')}
                type="select"
              >
                <option value="" key="0" />
                {draftRequestTypes
                  ? draftRequestTypes.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="draft-type-acceptableProductTypes"
                name="acceptableProductTypes"
                data-cy="acceptableProductTypes"
                label={translate('tfbitaApp.draftType.acceptableProductTypes')}
                type="select"
              >
                <option value="" key="0" />
                {objectiveCategoryElements
                  ? objectiveCategoryElements.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                label={translate('tfbitaApp.draftType.userGroups')}
                id="draft-type-userGroups"
                data-cy="userGroups"
                type="select"
                multiple
                name="userGroups"
              >
                <option value="" key="0" />
                {stringValues
                  ? stringValues.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/draft-type" replace color="info">
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

export default DraftTypeUpdate;
