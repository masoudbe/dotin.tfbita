import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, openFile, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './draft-tax.reducer';

export const DraftTaxDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const draftTaxEntity = useAppSelector(state => state.draftTax.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="draftTaxDetailsHeading">
          <Translate contentKey="tfbitaApp.draftTax.detail.title">DraftTax</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{draftTaxEntity.id}</dd>
          <dt>
            <span id="orderRegCurrencyAmount">
              <Translate contentKey="tfbitaApp.draftTax.orderRegCurrencyAmount">Order Reg Currency Amount</Translate>
            </span>
          </dt>
          <dd>{draftTaxEntity.orderRegCurrencyAmount}</dd>
          <dt>
            <span id="mainAccountCurrencyAmount">
              <Translate contentKey="tfbitaApp.draftTax.mainAccountCurrencyAmount">Main Account Currency Amount</Translate>
            </span>
          </dt>
          <dd>{draftTaxEntity.mainAccountCurrencyAmount}</dd>
          <dt>
            <span id="letterNumber">
              <Translate contentKey="tfbitaApp.draftTax.letterNumber">Letter Number</Translate>
            </span>
          </dt>
          <dd>{draftTaxEntity.letterNumber}</dd>
          <dt>
            <span id="letterImage">
              <Translate contentKey="tfbitaApp.draftTax.letterImage">Letter Image</Translate>
            </span>
          </dt>
          <dd>
            {draftTaxEntity.letterImage ? (
              <div>
                {draftTaxEntity.letterImageContentType ? (
                  <a onClick={openFile(draftTaxEntity.letterImageContentType, draftTaxEntity.letterImage)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                ) : null}
                <span>
                  {draftTaxEntity.letterImageContentType}, {byteSize(draftTaxEntity.letterImage)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="description">
              <Translate contentKey="tfbitaApp.draftTax.description">Description</Translate>
            </span>
          </dt>
          <dd>{draftTaxEntity.description}</dd>
          <dt>
            <span id="registrationDate">
              <Translate contentKey="tfbitaApp.draftTax.registrationDate">Registration Date</Translate>
            </span>
          </dt>
          <dd>{draftTaxEntity.registrationDate}</dd>
          <dt>
            <span id="returnTaxesAmount">
              <Translate contentKey="tfbitaApp.draftTax.returnTaxesAmount">Return Taxes Amount</Translate>
            </span>
          </dt>
          <dd>{draftTaxEntity.returnTaxesAmount ? 'true' : 'false'}</dd>
          <dt>
            <span id="orderRegRate">
              <Translate contentKey="tfbitaApp.draftTax.orderRegRate">Order Reg Rate</Translate>
            </span>
          </dt>
          <dd>{draftTaxEntity.orderRegRate}</dd>
          <dt>
            <span id="mainAccountRate">
              <Translate contentKey="tfbitaApp.draftTax.mainAccountRate">Main Account Rate</Translate>
            </span>
          </dt>
          <dd>{draftTaxEntity.mainAccountRate}</dd>
          <dt>
            <span id="documentTransactionNumber">
              <Translate contentKey="tfbitaApp.draftTax.documentTransactionNumber">Document Transaction Number</Translate>
            </span>
          </dt>
          <dd>{draftTaxEntity.documentTransactionNumber}</dd>
          <dt>
            <span id="returnDocumentTransactionNumber">
              <Translate contentKey="tfbitaApp.draftTax.returnDocumentTransactionNumber">Return Document Transaction Number</Translate>
            </span>
          </dt>
          <dd>{draftTaxEntity.returnDocumentTransactionNumber}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draftTax.taxes">Taxes</Translate>
          </dt>
          <dd>{draftTaxEntity.taxes ? draftTaxEntity.taxes.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/draft-tax" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/draft-tax/${draftTaxEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DraftTaxDetail;
