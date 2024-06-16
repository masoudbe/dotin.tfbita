import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './category-element.reducer';

export const CategoryElementDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const categoryElementEntity = useAppSelector(state => state.categoryElement.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="categoryElementDetailsHeading">
          <Translate contentKey="tfbitaApp.categoryElement.detail.title">CategoryElement</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{categoryElementEntity.id}</dd>
          <dt>
            <span id="value">
              <Translate contentKey="tfbitaApp.categoryElement.value">Value</Translate>
            </span>
          </dt>
          <dd>{categoryElementEntity.value}</dd>
          <dt>
            <span id="categoryName">
              <Translate contentKey="tfbitaApp.categoryElement.categoryName">Category Name</Translate>
            </span>
          </dt>
          <dd>{categoryElementEntity.categoryName}</dd>
          <dt>
            <span id="code">
              <Translate contentKey="tfbitaApp.categoryElement.code">Code</Translate>
            </span>
          </dt>
          <dd>{categoryElementEntity.code}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.orderRegType">Order Reg Type</Translate>
          </dt>
          <dd>{categoryElementEntity.orderRegType ? categoryElementEntity.orderRegType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.requestType">Request Type</Translate>
          </dt>
          <dd>{categoryElementEntity.requestType ? categoryElementEntity.requestType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.importType">Import Type</Translate>
          </dt>
          <dd>{categoryElementEntity.importType ? categoryElementEntity.importType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.operationTyp">Operation Typ</Translate>
          </dt>
          <dd>{categoryElementEntity.operationTyp ? categoryElementEntity.operationTyp.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.currencyProvisionType">Currency Provision Type</Translate>
          </dt>
          <dd>{categoryElementEntity.currencyProvisionType ? categoryElementEntity.currencyProvisionType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.paymentTool">Payment Tool</Translate>
          </dt>
          <dd>{categoryElementEntity.paymentTool ? categoryElementEntity.paymentTool.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.activityType">Activity Type</Translate>
          </dt>
          <dd>{categoryElementEntity.activityType ? categoryElementEntity.activityType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.ownerType">Owner Type</Translate>
          </dt>
          <dd>{categoryElementEntity.ownerType ? categoryElementEntity.ownerType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.status">Status</Translate>
          </dt>
          <dd>{categoryElementEntity.status ? categoryElementEntity.status.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.externalCustomerType">External Customer Type</Translate>
          </dt>
          <dd>{categoryElementEntity.externalCustomerType ? categoryElementEntity.externalCustomerType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.transportType">Transport Type</Translate>
          </dt>
          <dd>{categoryElementEntity.transportType ? categoryElementEntity.transportType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.currencySupplier">Currency Supplier</Translate>
          </dt>
          <dd>{categoryElementEntity.currencySupplier ? categoryElementEntity.currencySupplier.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.statusPurchase">Status Purchase</Translate>
          </dt>
          <dd>{categoryElementEntity.statusPurchase ? categoryElementEntity.statusPurchase.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.transportVehicleType">Transport Vehicle Type</Translate>
          </dt>
          <dd>{categoryElementEntity.transportVehicleType ? categoryElementEntity.transportVehicleType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.freightLetterType">Freight Letter Type</Translate>
          </dt>
          <dd>{categoryElementEntity.freightLetterType ? categoryElementEntity.freightLetterType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.actionCode">Action Code</Translate>
          </dt>
          <dd>{categoryElementEntity.actionCode ? categoryElementEntity.actionCode.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.ownershipCode">Ownership Code</Translate>
          </dt>
          <dd>{categoryElementEntity.ownershipCode ? categoryElementEntity.ownershipCode.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.currencyContainerPlace">Currency Container Place</Translate>
          </dt>
          <dd>{categoryElementEntity.currencyContainerPlace ? categoryElementEntity.currencyContainerPlace.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.draftSource">Draft Source</Translate>
          </dt>
          <dd>{categoryElementEntity.draftSource ? categoryElementEntity.draftSource.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.chargedExchangeBroker">Charged Exchange Broker</Translate>
          </dt>
          <dd>{categoryElementEntity.chargedExchangeBroker ? categoryElementEntity.chargedExchangeBroker.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.impartType">Impart Type</Translate>
          </dt>
          <dd>{categoryElementEntity.impartType ? categoryElementEntity.impartType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.insuranceLetterType">Insurance Letter Type</Translate>
          </dt>
          <dd>{categoryElementEntity.insuranceLetterType ? categoryElementEntity.insuranceLetterType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.advisorDepositType">Advisor Deposit Type</Translate>
          </dt>
          <dd>{categoryElementEntity.advisorDepositType ? categoryElementEntity.advisorDepositType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.interfaceAdvisorDepositType">Interface Advisor Deposit Type</Translate>
          </dt>
          <dd>{categoryElementEntity.interfaceAdvisorDepositType ? categoryElementEntity.interfaceAdvisorDepositType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.paymentType">Payment Type</Translate>
          </dt>
          <dd>{categoryElementEntity.paymentType ? categoryElementEntity.paymentType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.dealType">Deal Type</Translate>
          </dt>
          <dd>{categoryElementEntity.dealType ? categoryElementEntity.dealType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.coveringAdvisorDepositType">Covering Advisor Deposit Type</Translate>
          </dt>
          <dd>{categoryElementEntity.coveringAdvisorDepositType ? categoryElementEntity.coveringAdvisorDepositType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.depositType">Deposit Type</Translate>
          </dt>
          <dd>{categoryElementEntity.depositType ? categoryElementEntity.depositType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.type">Type</Translate>
          </dt>
          <dd>{categoryElementEntity.type ? categoryElementEntity.type.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.secondaryType">Secondary Type</Translate>
          </dt>
          <dd>{categoryElementEntity.secondaryType ? categoryElementEntity.secondaryType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.division">Division</Translate>
          </dt>
          <dd>{categoryElementEntity.division ? categoryElementEntity.division.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.productDimension">Product Dimension</Translate>
          </dt>
          <dd>{categoryElementEntity.productDimension ? categoryElementEntity.productDimension.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.stateOfDocuments">State Of Documents</Translate>
          </dt>
          <dd>{categoryElementEntity.stateOfDocuments ? categoryElementEntity.stateOfDocuments.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.currencyProvisionFileType">Currency Provision File Type</Translate>
          </dt>
          <dd>{categoryElementEntity.currencyProvisionFileType ? categoryElementEntity.currencyProvisionFileType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.statusDraft">Status Draft</Translate>
          </dt>
          <dd>{categoryElementEntity.statusDraft ? categoryElementEntity.statusDraft.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/category-element" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/category-element/${categoryElementEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CategoryElementDetail;
