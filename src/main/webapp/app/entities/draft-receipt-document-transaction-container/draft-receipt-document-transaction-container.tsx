import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './draft-receipt-document-transaction-container.reducer';

export const DraftReceiptDocumentTransactionContainer = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const draftReceiptDocumentTransactionContainerList = useAppSelector(state => state.draftReceiptDocumentTransactionContainer.entities);
  const loading = useAppSelector(state => state.draftReceiptDocumentTransactionContainer.loading);

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
      <h2 id="draft-receipt-document-transaction-container-heading" data-cy="DraftReceiptDocumentTransactionContainerHeading">
        <Translate contentKey="tfbitaApp.draftReceiptDocumentTransactionContainer.home.title">
          Draft Receipt Document Transaction Containers
        </Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="tfbitaApp.draftReceiptDocumentTransactionContainer.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/draft-receipt-document-transaction-container/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="tfbitaApp.draftReceiptDocumentTransactionContainer.home.createLabel">
              Create new Draft Receipt Document Transaction Container
            </Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {draftReceiptDocumentTransactionContainerList && draftReceiptDocumentTransactionContainerList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="tfbitaApp.draftReceiptDocumentTransactionContainer.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('receiveReceiptCommission')}>
                  <Translate contentKey="tfbitaApp.draftReceiptDocumentTransactionContainer.receiveReceiptCommission">
                    Receive Receipt Commission
                  </Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('receiveReceiptCommission')} />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.draftReceiptDocumentTransactionContainer.receiptIssueDocumentTransaction">
                    Receipt Issue Document Transaction
                  </Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.draftReceiptDocumentTransactionContainer.freightLetterStampCostDocumentTransaction">
                    Freight Letter Stamp Cost Document Transaction
                  </Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.draftReceiptDocumentTransactionContainer.deliverDocumentTransaction">
                    Deliver Document Transaction
                  </Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.draftReceiptDocumentTransactionContainer.documentTransactionCanceledDeliver">
                    Document Transaction Canceled Deliver
                  </Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.draftReceiptDocumentTransactionContainer.documentTransactionCanceledReceiptIssue">
                    Document Transaction Canceled Receipt Issue
                  </Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.draftReceiptDocumentTransactionContainer.receiptCommissionDocumentTransactions">
                    Receipt Commission Document Transactions
                  </Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.draftReceiptDocumentTransactionContainer.draftDocumentTransactionContainer">
                    Draft Document Transaction Container
                  </Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {draftReceiptDocumentTransactionContainerList.map((draftReceiptDocumentTransactionContainer, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button
                      tag={Link}
                      to={`/draft-receipt-document-transaction-container/${draftReceiptDocumentTransactionContainer.id}`}
                      color="link"
                      size="sm"
                    >
                      {draftReceiptDocumentTransactionContainer.id}
                    </Button>
                  </td>
                  <td>{draftReceiptDocumentTransactionContainer.receiveReceiptCommission ? 'true' : 'false'}</td>
                  <td>
                    {draftReceiptDocumentTransactionContainer.receiptIssueDocumentTransaction ? (
                      <Link to={`/document-transaction/${draftReceiptDocumentTransactionContainer.receiptIssueDocumentTransaction.id}`}>
                        {draftReceiptDocumentTransactionContainer.receiptIssueDocumentTransaction.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {draftReceiptDocumentTransactionContainer.freightLetterStampCostDocumentTransaction ? (
                      <Link
                        to={`/document-transaction/${draftReceiptDocumentTransactionContainer.freightLetterStampCostDocumentTransaction.id}`}
                      >
                        {draftReceiptDocumentTransactionContainer.freightLetterStampCostDocumentTransaction.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {draftReceiptDocumentTransactionContainer.deliverDocumentTransaction ? (
                      <Link to={`/document-transaction/${draftReceiptDocumentTransactionContainer.deliverDocumentTransaction.id}`}>
                        {draftReceiptDocumentTransactionContainer.deliverDocumentTransaction.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {draftReceiptDocumentTransactionContainer.documentTransactionCanceledDeliver ? (
                      <Link to={`/document-transaction/${draftReceiptDocumentTransactionContainer.documentTransactionCanceledDeliver.id}`}>
                        {draftReceiptDocumentTransactionContainer.documentTransactionCanceledDeliver.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {draftReceiptDocumentTransactionContainer.documentTransactionCanceledReceiptIssue ? (
                      <Link
                        to={`/document-transaction/${draftReceiptDocumentTransactionContainer.documentTransactionCanceledReceiptIssue.id}`}
                      >
                        {draftReceiptDocumentTransactionContainer.documentTransactionCanceledReceiptIssue.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {draftReceiptDocumentTransactionContainer.receiptCommissionDocumentTransactions ? (
                      <Link
                        to={`/document-transaction/${draftReceiptDocumentTransactionContainer.receiptCommissionDocumentTransactions.id}`}
                      >
                        {draftReceiptDocumentTransactionContainer.receiptCommissionDocumentTransactions.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {draftReceiptDocumentTransactionContainer.draftDocumentTransactionContainer ? (
                      <Link
                        to={`/draft-document-transaction-container/${draftReceiptDocumentTransactionContainer.draftDocumentTransactionContainer.id}`}
                      >
                        {draftReceiptDocumentTransactionContainer.draftDocumentTransactionContainer.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/draft-receipt-document-transaction-container/${draftReceiptDocumentTransactionContainer.id}`}
                        color="info"
                        size="sm"
                        data-cy="entityDetailsButton"
                      >
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/draft-receipt-document-transaction-container/${draftReceiptDocumentTransactionContainer.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() =>
                          (window.location.href = `/draft-receipt-document-transaction-container/${draftReceiptDocumentTransactionContainer.id}/delete`)
                        }
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
              <Translate contentKey="tfbitaApp.draftReceiptDocumentTransactionContainer.home.notFound">
                No Draft Receipt Document Transaction Containers found
              </Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default DraftReceiptDocumentTransactionContainer;
