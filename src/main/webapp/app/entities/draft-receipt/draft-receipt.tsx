import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { openFile, byteSize, Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './draft-receipt.reducer';

export const DraftReceipt = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const draftReceiptList = useAppSelector(state => state.draftReceipt.entities);
  const loading = useAppSelector(state => state.draftReceipt.loading);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        sort: `${sortState.sort},${sortState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?sort=${sortState.sort},${sortState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [sortState.order, sortState.sort]);

  const sort = p => () => {
    setSortState({
      ...sortState,
      order: sortState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = sortState.sort;
    const order = sortState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    } else {
      return order === ASC ? faSortUp : faSortDown;
    }
  };

  return (
    <div>
      <h2 id="draft-receipt-heading" data-cy="DraftReceiptHeading">
        <Translate contentKey="tfbitaApp.draftReceipt.home.title">Draft Receipts</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="tfbitaApp.draftReceipt.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/draft-receipt/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="tfbitaApp.draftReceipt.home.createLabel">Create new Draft Receipt</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {draftReceiptList && draftReceiptList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="tfbitaApp.draftReceipt.id">ID</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('comment')}>
                  <Translate contentKey="tfbitaApp.draftReceipt.comment">Comment</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('comment')} />
                </th>
                <th className="hand" onClick={sort('customerDeliverDate')}>
                  <Translate contentKey="tfbitaApp.draftReceipt.customerDeliverDate">Customer Deliver Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('customerDeliverDate')} />
                </th>
                <th className="hand" onClick={sort('date')}>
                  <Translate contentKey="tfbitaApp.draftReceipt.date">Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('date')} />
                </th>
                <th className="hand" onClick={sort('deleteDate')}>
                  <Translate contentKey="tfbitaApp.draftReceipt.deleteDate">Delete Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('deleteDate')} />
                </th>
                <th className="hand" onClick={sort('deliverDate')}>
                  <Translate contentKey="tfbitaApp.draftReceipt.deliverDate">Deliver Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('deliverDate')} />
                </th>
                <th className="hand" onClick={sort('deliverDuration')}>
                  <Translate contentKey="tfbitaApp.draftReceipt.deliverDuration">Deliver Duration</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('deliverDuration')} />
                </th>
                <th className="hand" onClick={sort('delivered')}>
                  <Translate contentKey="tfbitaApp.draftReceipt.delivered">Delivered</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('delivered')} />
                </th>
                <th className="hand" onClick={sort('documentTransactionEffectiveDate')}>
                  <Translate contentKey="tfbitaApp.draftReceipt.documentTransactionEffectiveDate">
                    Document Transaction Effective Date
                  </Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('documentTransactionEffectiveDate')} />
                </th>
                <th className="hand" onClick={sort('freightLetterDate')}>
                  <Translate contentKey="tfbitaApp.draftReceipt.freightLetterDate">Freight Letter Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('freightLetterDate')} />
                </th>
                <th className="hand" onClick={sort('freightLetterNumber')}>
                  <Translate contentKey="tfbitaApp.draftReceipt.freightLetterNumber">Freight Letter Number</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('freightLetterNumber')} />
                </th>
                <th className="hand" onClick={sort('freightLetterStampCost')}>
                  <Translate contentKey="tfbitaApp.draftReceipt.freightLetterStampCost">Freight Letter Stamp Cost</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('freightLetterStampCost')} />
                </th>
                <th className="hand" onClick={sort('issueDate')}>
                  <Translate contentKey="tfbitaApp.draftReceipt.issueDate">Issue Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('issueDate')} />
                </th>
                <th className="hand" onClick={sort('issueDocument')}>
                  <Translate contentKey="tfbitaApp.draftReceipt.issueDocument">Issue Document</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('issueDocument')} />
                </th>
                <th className="hand" onClick={sort('productAmount')}>
                  <Translate contentKey="tfbitaApp.draftReceipt.productAmount">Product Amount</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('productAmount')} />
                </th>
                <th className="hand" onClick={sort('receiptAmount')}>
                  <Translate contentKey="tfbitaApp.draftReceipt.receiptAmount">Receipt Amount</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('receiptAmount')} />
                </th>
                <th className="hand" onClick={sort('receiptDate')}>
                  <Translate contentKey="tfbitaApp.draftReceipt.receiptDate">Receipt Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('receiptDate')} />
                </th>
                <th className="hand" onClick={sort('row')}>
                  <Translate contentKey="tfbitaApp.draftReceipt.row">Row</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('row')} />
                </th>
                <th className="hand" onClick={sort('serial')}>
                  <Translate contentKey="tfbitaApp.draftReceipt.serial">Serial</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('serial')} />
                </th>
                <th className="hand" onClick={sort('transportRow')}>
                  <Translate contentKey="tfbitaApp.draftReceipt.transportRow">Transport Row</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('transportRow')} />
                </th>
                <th className="hand" onClick={sort('usable')}>
                  <Translate contentKey="tfbitaApp.draftReceipt.usable">Usable</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('usable')} />
                </th>
                <th className="hand" onClick={sort('paymentCurrencyRateTypeDesc')}>
                  <Translate contentKey="tfbitaApp.draftReceipt.paymentCurrencyRateTypeDesc">Payment Currency Rate Type Desc</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('paymentCurrencyRateTypeDesc')} />
                </th>
                <th className="hand" onClick={sort('paymentItemTypeDesc')}>
                  <Translate contentKey="tfbitaApp.draftReceipt.paymentItemTypeDesc">Payment Item Type Desc</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('paymentItemTypeDesc')} />
                </th>
                <th className="hand" onClick={sort('netWeight')}>
                  <Translate contentKey="tfbitaApp.draftReceipt.netWeight">Net Weight</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('netWeight')} />
                </th>
                <th className="hand" onClick={sort('grossWeight')}>
                  <Translate contentKey="tfbitaApp.draftReceipt.grossWeight">Gross Weight</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('grossWeight')} />
                </th>
                <th className="hand" onClick={sort('totalNetWeight')}>
                  <Translate contentKey="tfbitaApp.draftReceipt.totalNetWeight">Total Net Weight</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('totalNetWeight')} />
                </th>
                <th className="hand" onClick={sort('totalGrossWeight')}>
                  <Translate contentKey="tfbitaApp.draftReceipt.totalGrossWeight">Total Gross Weight</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('totalGrossWeight')} />
                </th>
                <th className="hand" onClick={sort('letterNumberTazirat')}>
                  <Translate contentKey="tfbitaApp.draftReceipt.letterNumberTazirat">Letter Number Tazirat</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('letterNumberTazirat')} />
                </th>
                <th className="hand" onClick={sort('letterDateTazirat')}>
                  <Translate contentKey="tfbitaApp.draftReceipt.letterDateTazirat">Letter Date Tazirat</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('letterDateTazirat')} />
                </th>
                <th className="hand" onClick={sort('fobAmount')}>
                  <Translate contentKey="tfbitaApp.draftReceipt.fobAmount">Fob Amount</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('fobAmount')} />
                </th>
                <th className="hand" onClick={sort('shippingFare')}>
                  <Translate contentKey="tfbitaApp.draftReceipt.shippingFare">Shipping Fare</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('shippingFare')} />
                </th>
                <th className="hand" onClick={sort('inspectionCost')}>
                  <Translate contentKey="tfbitaApp.draftReceipt.inspectionCost">Inspection Cost</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('inspectionCost')} />
                </th>
                <th className="hand" onClick={sort('discount')}>
                  <Translate contentKey="tfbitaApp.draftReceipt.discount">Discount</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('discount')} />
                </th>
                <th className="hand" onClick={sort('deadlineSubmitDocumentDate')}>
                  <Translate contentKey="tfbitaApp.draftReceipt.deadlineSubmitDocumentDate">Deadline Submit Document Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('deadlineSubmitDocumentDate')} />
                </th>
                <th className="hand" onClick={sort('currencyProvisionFile')}>
                  <Translate contentKey="tfbitaApp.draftReceipt.currencyProvisionFile">Currency Provision File</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('currencyProvisionFile')} />
                </th>
                <th className="hand" onClick={sort('isMigrational')}>
                  <Translate contentKey="tfbitaApp.draftReceipt.isMigrational">Is Migrational</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isMigrational')} />
                </th>
                <th className="hand" onClick={sort('otherCost')}>
                  <Translate contentKey="tfbitaApp.draftReceipt.otherCost">Other Cost</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('otherCost')} />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.draftReceipt.receipts">Receipts</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.draftReceipt.draftCustomJustification">Draft Custom Justification</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {draftReceiptList.map((draftReceipt, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/draft-receipt/${draftReceipt.id}`} color="link" size="sm">
                      {draftReceipt.id}
                    </Button>
                  </td>
                  <td>{draftReceipt.comment}</td>
                  <td>{draftReceipt.customerDeliverDate}</td>
                  <td>{draftReceipt.date}</td>
                  <td>{draftReceipt.deleteDate}</td>
                  <td>{draftReceipt.deliverDate}</td>
                  <td>{draftReceipt.deliverDuration}</td>
                  <td>{draftReceipt.delivered ? 'true' : 'false'}</td>
                  <td>{draftReceipt.documentTransactionEffectiveDate}</td>
                  <td>{draftReceipt.freightLetterDate}</td>
                  <td>{draftReceipt.freightLetterNumber}</td>
                  <td>{draftReceipt.freightLetterStampCost}</td>
                  <td>{draftReceipt.issueDate}</td>
                  <td>{draftReceipt.issueDocument ? 'true' : 'false'}</td>
                  <td>{draftReceipt.productAmount}</td>
                  <td>{draftReceipt.receiptAmount}</td>
                  <td>{draftReceipt.receiptDate}</td>
                  <td>{draftReceipt.row}</td>
                  <td>{draftReceipt.serial}</td>
                  <td>{draftReceipt.transportRow}</td>
                  <td>{draftReceipt.usable ? 'true' : 'false'}</td>
                  <td>{draftReceipt.paymentCurrencyRateTypeDesc}</td>
                  <td>{draftReceipt.paymentItemTypeDesc}</td>
                  <td>{draftReceipt.netWeight}</td>
                  <td>{draftReceipt.grossWeight}</td>
                  <td>{draftReceipt.totalNetWeight}</td>
                  <td>{draftReceipt.totalGrossWeight}</td>
                  <td>{draftReceipt.letterNumberTazirat}</td>
                  <td>{draftReceipt.letterDateTazirat}</td>
                  <td>{draftReceipt.fobAmount}</td>
                  <td>{draftReceipt.shippingFare}</td>
                  <td>{draftReceipt.inspectionCost}</td>
                  <td>{draftReceipt.discount}</td>
                  <td>{draftReceipt.deadlineSubmitDocumentDate}</td>
                  <td>
                    {draftReceipt.currencyProvisionFile ? (
                      <div>
                        {draftReceipt.currencyProvisionFileContentType ? (
                          <a onClick={openFile(draftReceipt.currencyProvisionFileContentType, draftReceipt.currencyProvisionFile)}>
                            <Translate contentKey="entity.action.open">Open</Translate>
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {draftReceipt.currencyProvisionFileContentType}, {byteSize(draftReceipt.currencyProvisionFile)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{draftReceipt.isMigrational ? 'true' : 'false'}</td>
                  <td>{draftReceipt.otherCost}</td>
                  <td>{draftReceipt.receipts ? <Link to={`/draft/${draftReceipt.receipts.id}`}>{draftReceipt.receipts.id}</Link> : ''}</td>
                  <td>
                    {draftReceipt.draftCustomJustifications
                      ? draftReceipt.draftCustomJustifications.map((val, j) => (
                          <span key={j}>
                            <Link to={`/draft-custom-justification/${val.id}`}>{val.id}</Link>
                            {j === draftReceipt.draftCustomJustifications.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/draft-receipt/${draftReceipt.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/draft-receipt/${draftReceipt.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/draft-receipt/${draftReceipt.id}/delete`)}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="tfbitaApp.draftReceipt.home.notFound">No Draft Receipts found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default DraftReceipt;
