import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, openFile, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './draft-receipt.reducer';

export const DraftReceiptDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const draftReceiptEntity = useAppSelector(state => state.draftReceipt.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="draftReceiptDetailsHeading">
          <Translate contentKey="tfbitaApp.draftReceipt.detail.title">DraftReceipt</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{draftReceiptEntity.id}</dd>
          <dt>
            <span id="comment">
              <Translate contentKey="tfbitaApp.draftReceipt.comment">Comment</Translate>
            </span>
          </dt>
          <dd>{draftReceiptEntity.comment}</dd>
          <dt>
            <span id="customerDeliverDate">
              <Translate contentKey="tfbitaApp.draftReceipt.customerDeliverDate">Customer Deliver Date</Translate>
            </span>
          </dt>
          <dd>{draftReceiptEntity.customerDeliverDate}</dd>
          <dt>
            <span id="date">
              <Translate contentKey="tfbitaApp.draftReceipt.date">Date</Translate>
            </span>
          </dt>
          <dd>{draftReceiptEntity.date}</dd>
          <dt>
            <span id="deleteDate">
              <Translate contentKey="tfbitaApp.draftReceipt.deleteDate">Delete Date</Translate>
            </span>
          </dt>
          <dd>{draftReceiptEntity.deleteDate}</dd>
          <dt>
            <span id="deliverDate">
              <Translate contentKey="tfbitaApp.draftReceipt.deliverDate">Deliver Date</Translate>
            </span>
          </dt>
          <dd>{draftReceiptEntity.deliverDate}</dd>
          <dt>
            <span id="deliverDuration">
              <Translate contentKey="tfbitaApp.draftReceipt.deliverDuration">Deliver Duration</Translate>
            </span>
          </dt>
          <dd>{draftReceiptEntity.deliverDuration}</dd>
          <dt>
            <span id="delivered">
              <Translate contentKey="tfbitaApp.draftReceipt.delivered">Delivered</Translate>
            </span>
          </dt>
          <dd>{draftReceiptEntity.delivered ? 'true' : 'false'}</dd>
          <dt>
            <span id="documentTransactionEffectiveDate">
              <Translate contentKey="tfbitaApp.draftReceipt.documentTransactionEffectiveDate">
                Document Transaction Effective Date
              </Translate>
            </span>
          </dt>
          <dd>{draftReceiptEntity.documentTransactionEffectiveDate}</dd>
          <dt>
            <span id="freightLetterDate">
              <Translate contentKey="tfbitaApp.draftReceipt.freightLetterDate">Freight Letter Date</Translate>
            </span>
          </dt>
          <dd>{draftReceiptEntity.freightLetterDate}</dd>
          <dt>
            <span id="freightLetterNumber">
              <Translate contentKey="tfbitaApp.draftReceipt.freightLetterNumber">Freight Letter Number</Translate>
            </span>
          </dt>
          <dd>{draftReceiptEntity.freightLetterNumber}</dd>
          <dt>
            <span id="freightLetterStampCost">
              <Translate contentKey="tfbitaApp.draftReceipt.freightLetterStampCost">Freight Letter Stamp Cost</Translate>
            </span>
          </dt>
          <dd>{draftReceiptEntity.freightLetterStampCost}</dd>
          <dt>
            <span id="issueDate">
              <Translate contentKey="tfbitaApp.draftReceipt.issueDate">Issue Date</Translate>
            </span>
          </dt>
          <dd>{draftReceiptEntity.issueDate}</dd>
          <dt>
            <span id="issueDocument">
              <Translate contentKey="tfbitaApp.draftReceipt.issueDocument">Issue Document</Translate>
            </span>
          </dt>
          <dd>{draftReceiptEntity.issueDocument ? 'true' : 'false'}</dd>
          <dt>
            <span id="productAmount">
              <Translate contentKey="tfbitaApp.draftReceipt.productAmount">Product Amount</Translate>
            </span>
          </dt>
          <dd>{draftReceiptEntity.productAmount}</dd>
          <dt>
            <span id="receiptAmount">
              <Translate contentKey="tfbitaApp.draftReceipt.receiptAmount">Receipt Amount</Translate>
            </span>
          </dt>
          <dd>{draftReceiptEntity.receiptAmount}</dd>
          <dt>
            <span id="receiptDate">
              <Translate contentKey="tfbitaApp.draftReceipt.receiptDate">Receipt Date</Translate>
            </span>
          </dt>
          <dd>{draftReceiptEntity.receiptDate}</dd>
          <dt>
            <span id="row">
              <Translate contentKey="tfbitaApp.draftReceipt.row">Row</Translate>
            </span>
          </dt>
          <dd>{draftReceiptEntity.row}</dd>
          <dt>
            <span id="serial">
              <Translate contentKey="tfbitaApp.draftReceipt.serial">Serial</Translate>
            </span>
          </dt>
          <dd>{draftReceiptEntity.serial}</dd>
          <dt>
            <span id="transportRow">
              <Translate contentKey="tfbitaApp.draftReceipt.transportRow">Transport Row</Translate>
            </span>
          </dt>
          <dd>{draftReceiptEntity.transportRow}</dd>
          <dt>
            <span id="usable">
              <Translate contentKey="tfbitaApp.draftReceipt.usable">Usable</Translate>
            </span>
          </dt>
          <dd>{draftReceiptEntity.usable ? 'true' : 'false'}</dd>
          <dt>
            <span id="paymentCurrencyRateTypeDesc">
              <Translate contentKey="tfbitaApp.draftReceipt.paymentCurrencyRateTypeDesc">Payment Currency Rate Type Desc</Translate>
            </span>
          </dt>
          <dd>{draftReceiptEntity.paymentCurrencyRateTypeDesc}</dd>
          <dt>
            <span id="paymentItemTypeDesc">
              <Translate contentKey="tfbitaApp.draftReceipt.paymentItemTypeDesc">Payment Item Type Desc</Translate>
            </span>
          </dt>
          <dd>{draftReceiptEntity.paymentItemTypeDesc}</dd>
          <dt>
            <span id="netWeight">
              <Translate contentKey="tfbitaApp.draftReceipt.netWeight">Net Weight</Translate>
            </span>
          </dt>
          <dd>{draftReceiptEntity.netWeight}</dd>
          <dt>
            <span id="grossWeight">
              <Translate contentKey="tfbitaApp.draftReceipt.grossWeight">Gross Weight</Translate>
            </span>
          </dt>
          <dd>{draftReceiptEntity.grossWeight}</dd>
          <dt>
            <span id="totalNetWeight">
              <Translate contentKey="tfbitaApp.draftReceipt.totalNetWeight">Total Net Weight</Translate>
            </span>
          </dt>
          <dd>{draftReceiptEntity.totalNetWeight}</dd>
          <dt>
            <span id="totalGrossWeight">
              <Translate contentKey="tfbitaApp.draftReceipt.totalGrossWeight">Total Gross Weight</Translate>
            </span>
          </dt>
          <dd>{draftReceiptEntity.totalGrossWeight}</dd>
          <dt>
            <span id="letterNumberTazirat">
              <Translate contentKey="tfbitaApp.draftReceipt.letterNumberTazirat">Letter Number Tazirat</Translate>
            </span>
          </dt>
          <dd>{draftReceiptEntity.letterNumberTazirat}</dd>
          <dt>
            <span id="letterDateTazirat">
              <Translate contentKey="tfbitaApp.draftReceipt.letterDateTazirat">Letter Date Tazirat</Translate>
            </span>
          </dt>
          <dd>{draftReceiptEntity.letterDateTazirat}</dd>
          <dt>
            <span id="fobAmount">
              <Translate contentKey="tfbitaApp.draftReceipt.fobAmount">Fob Amount</Translate>
            </span>
          </dt>
          <dd>{draftReceiptEntity.fobAmount}</dd>
          <dt>
            <span id="shippingFare">
              <Translate contentKey="tfbitaApp.draftReceipt.shippingFare">Shipping Fare</Translate>
            </span>
          </dt>
          <dd>{draftReceiptEntity.shippingFare}</dd>
          <dt>
            <span id="inspectionCost">
              <Translate contentKey="tfbitaApp.draftReceipt.inspectionCost">Inspection Cost</Translate>
            </span>
          </dt>
          <dd>{draftReceiptEntity.inspectionCost}</dd>
          <dt>
            <span id="discount">
              <Translate contentKey="tfbitaApp.draftReceipt.discount">Discount</Translate>
            </span>
          </dt>
          <dd>{draftReceiptEntity.discount}</dd>
          <dt>
            <span id="deadlineSubmitDocumentDate">
              <Translate contentKey="tfbitaApp.draftReceipt.deadlineSubmitDocumentDate">Deadline Submit Document Date</Translate>
            </span>
          </dt>
          <dd>{draftReceiptEntity.deadlineSubmitDocumentDate}</dd>
          <dt>
            <span id="currencyProvisionFile">
              <Translate contentKey="tfbitaApp.draftReceipt.currencyProvisionFile">Currency Provision File</Translate>
            </span>
          </dt>
          <dd>
            {draftReceiptEntity.currencyProvisionFile ? (
              <div>
                {draftReceiptEntity.currencyProvisionFileContentType ? (
                  <a onClick={openFile(draftReceiptEntity.currencyProvisionFileContentType, draftReceiptEntity.currencyProvisionFile)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                ) : null}
                <span>
                  {draftReceiptEntity.currencyProvisionFileContentType}, {byteSize(draftReceiptEntity.currencyProvisionFile)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="isMigrational">
              <Translate contentKey="tfbitaApp.draftReceipt.isMigrational">Is Migrational</Translate>
            </span>
          </dt>
          <dd>{draftReceiptEntity.isMigrational ? 'true' : 'false'}</dd>
          <dt>
            <span id="otherCost">
              <Translate contentKey="tfbitaApp.draftReceipt.otherCost">Other Cost</Translate>
            </span>
          </dt>
          <dd>{draftReceiptEntity.otherCost}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draftReceipt.receipts">Receipts</Translate>
          </dt>
          <dd>{draftReceiptEntity.receipts ? draftReceiptEntity.receipts.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draftReceipt.draftCustomJustification">Draft Custom Justification</Translate>
          </dt>
          <dd>
            {draftReceiptEntity.draftCustomJustifications
              ? draftReceiptEntity.draftCustomJustifications.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {draftReceiptEntity.draftCustomJustifications && i === draftReceiptEntity.draftCustomJustifications.length - 1
                      ? ''
                      : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/draft-receipt" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/draft-receipt/${draftReceiptEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DraftReceiptDetail;
