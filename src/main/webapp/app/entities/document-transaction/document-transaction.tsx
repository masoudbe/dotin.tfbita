import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './document-transaction.reducer';

export const DocumentTransaction = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const documentTransactionList = useAppSelector(state => state.documentTransaction.entities);
  const loading = useAppSelector(state => state.documentTransaction.loading);

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
      <h2 id="document-transaction-heading" data-cy="DocumentTransactionHeading">
        <Translate contentKey="tfbitaApp.documentTransaction.home.title">Document Transactions</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="tfbitaApp.documentTransaction.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/document-transaction/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="tfbitaApp.documentTransaction.home.createLabel">Create new Document Transaction</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {documentTransactionList && documentTransactionList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="tfbitaApp.documentTransaction.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('currencyExchangeCode')}>
                  <Translate contentKey="tfbitaApp.documentTransaction.currencyExchangeCode">Currency Exchange Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('currencyExchangeCode')} />
                </th>
                <th className="hand" onClick={sort('transactionCode')}>
                  <Translate contentKey="tfbitaApp.documentTransaction.transactionCode">Transaction Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('transactionCode')} />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.documentTransaction.otherDocumentTransactionsContainer">
                    Other Document Transactions Container
                  </Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.documentTransaction.canceledJustificationDocumentContainer">
                    Canceled Justification Document Container
                  </Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.documentTransaction.justificationDocumentTransactionsContainer">
                    Justification Document Transactions Container
                  </Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.documentTransaction.receivedCommisionsContainer">
                    Received Commisions Container
                  </Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.documentTransaction.canceledDocumentTransactionsContainer">
                    Canceled Document Transactions Container
                  </Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.documentTransaction.returnedDefaultCurrencyCostsContainer">
                    Returned Default Currency Costs Container
                  </Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.documentTransaction.defaultCurrencyCostsDocumentContainer">
                    Default Currency Costs Document Container
                  </Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.documentTransaction.customJustification">Custom Justification</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {documentTransactionList.map((documentTransaction, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/document-transaction/${documentTransaction.id}`} color="link" size="sm">
                      {documentTransaction.id}
                    </Button>
                  </td>
                  <td>{documentTransaction.currencyExchangeCode}</td>
                  <td>{documentTransaction.transactionCode}</td>
                  <td>
                    {documentTransaction.otherDocumentTransactionsContainer ? (
                      <Link to={`/draft-document-transaction-container/${documentTransaction.otherDocumentTransactionsContainer.id}`}>
                        {documentTransaction.otherDocumentTransactionsContainer.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {documentTransaction.canceledJustificationDocumentContainer ? (
                      <Link to={`/draft-document-transaction-container/${documentTransaction.canceledJustificationDocumentContainer.id}`}>
                        {documentTransaction.canceledJustificationDocumentContainer.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {documentTransaction.justificationDocumentTransactionsContainer ? (
                      <Link
                        to={`/draft-document-transaction-container/${documentTransaction.justificationDocumentTransactionsContainer.id}`}
                      >
                        {documentTransaction.justificationDocumentTransactionsContainer.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {documentTransaction.receivedCommisionsContainer ? (
                      <Link to={`/draft-document-transaction-container/${documentTransaction.receivedCommisionsContainer.id}`}>
                        {documentTransaction.receivedCommisionsContainer.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {documentTransaction.canceledDocumentTransactionsContainer ? (
                      <Link to={`/draft-document-transaction-container/${documentTransaction.canceledDocumentTransactionsContainer.id}`}>
                        {documentTransaction.canceledDocumentTransactionsContainer.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {documentTransaction.returnedDefaultCurrencyCostsContainer ? (
                      <Link to={`/draft-document-transaction-container/${documentTransaction.returnedDefaultCurrencyCostsContainer.id}`}>
                        {documentTransaction.returnedDefaultCurrencyCostsContainer.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {documentTransaction.defaultCurrencyCostsDocumentContainer ? (
                      <Link to={`/draft-document-transaction-container/${documentTransaction.defaultCurrencyCostsDocumentContainer.id}`}>
                        {documentTransaction.defaultCurrencyCostsDocumentContainer.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {documentTransaction.customJustifications
                      ? documentTransaction.customJustifications.map((val, j) => (
                          <span key={j}>
                            <Link to={`/custom-justification/${val.id}`}>{val.id}</Link>
                            {j === documentTransaction.customJustifications.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/document-transaction/${documentTransaction.id}`}
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
                        to={`/document-transaction/${documentTransaction.id}/edit`}
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
                        onClick={() => (window.location.href = `/document-transaction/${documentTransaction.id}/delete`)}
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
              <Translate contentKey="tfbitaApp.documentTransaction.home.notFound">No Document Transactions found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default DocumentTransaction;
