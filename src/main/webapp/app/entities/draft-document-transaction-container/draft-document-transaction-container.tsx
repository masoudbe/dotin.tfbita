import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './draft-document-transaction-container.reducer';

export const DraftDocumentTransactionContainer = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const draftDocumentTransactionContainerList = useAppSelector(state => state.draftDocumentTransactionContainer.entities);
  const loading = useAppSelector(state => state.draftDocumentTransactionContainer.loading);

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
      <h2 id="draft-document-transaction-container-heading" data-cy="DraftDocumentTransactionContainerHeading">
        <Translate contentKey="tfbitaApp.draftDocumentTransactionContainer.home.title">Draft Document Transaction Containers</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="tfbitaApp.draftDocumentTransactionContainer.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/draft-document-transaction-container/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="tfbitaApp.draftDocumentTransactionContainer.home.createLabel">
              Create new Draft Document Transaction Container
            </Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {draftDocumentTransactionContainerList && draftDocumentTransactionContainerList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="tfbitaApp.draftDocumentTransactionContainer.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.draftDocumentTransactionContainer.issueCommissionDocumentTransaction">
                    Issue Commission Document Transaction
                  </Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.draftDocumentTransactionContainer.paymentDocumentTransaction">
                    Payment Document Transaction
                  </Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.draftDocumentTransactionContainer.settleDocumentTransaction">
                    Settle Document Transaction
                  </Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.draftDocumentTransactionContainer.settleExcessDocumentTransaction">
                    Settle Excess Document Transaction
                  </Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.draftDocumentTransactionContainer.commissionDeleteDraftDocumentTransaction">
                    Commission Delete Draft Document Transaction
                  </Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.draftDocumentTransactionContainer.commissionDraftExtendDocumentTransaction">
                    Commission Draft Extend Document Transaction
                  </Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.draftDocumentTransactionContainer.draft">Draft</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {draftDocumentTransactionContainerList.map((draftDocumentTransactionContainer, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button
                      tag={Link}
                      to={`/draft-document-transaction-container/${draftDocumentTransactionContainer.id}`}
                      color="link"
                      size="sm"
                    >
                      {draftDocumentTransactionContainer.id}
                    </Button>
                  </td>
                  <td>
                    {draftDocumentTransactionContainer.issueCommissionDocumentTransaction ? (
                      <Link to={`/document-transaction/${draftDocumentTransactionContainer.issueCommissionDocumentTransaction.id}`}>
                        {draftDocumentTransactionContainer.issueCommissionDocumentTransaction.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {draftDocumentTransactionContainer.paymentDocumentTransaction ? (
                      <Link to={`/document-transaction/${draftDocumentTransactionContainer.paymentDocumentTransaction.id}`}>
                        {draftDocumentTransactionContainer.paymentDocumentTransaction.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {draftDocumentTransactionContainer.settleDocumentTransaction ? (
                      <Link to={`/document-transaction/${draftDocumentTransactionContainer.settleDocumentTransaction.id}`}>
                        {draftDocumentTransactionContainer.settleDocumentTransaction.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {draftDocumentTransactionContainer.settleExcessDocumentTransaction ? (
                      <Link to={`/document-transaction/${draftDocumentTransactionContainer.settleExcessDocumentTransaction.id}`}>
                        {draftDocumentTransactionContainer.settleExcessDocumentTransaction.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {draftDocumentTransactionContainer.commissionDeleteDraftDocumentTransaction ? (
                      <Link to={`/document-transaction/${draftDocumentTransactionContainer.commissionDeleteDraftDocumentTransaction.id}`}>
                        {draftDocumentTransactionContainer.commissionDeleteDraftDocumentTransaction.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {draftDocumentTransactionContainer.commissionDraftExtendDocumentTransaction ? (
                      <Link to={`/document-transaction/${draftDocumentTransactionContainer.commissionDraftExtendDocumentTransaction.id}`}>
                        {draftDocumentTransactionContainer.commissionDraftExtendDocumentTransaction.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {draftDocumentTransactionContainer.drafts
                      ? draftDocumentTransactionContainer.drafts.map((val, j) => (
                          <span key={j}>
                            <Link to={`/draft/${val.id}`}>{val.id}</Link>
                            {j === draftDocumentTransactionContainer.drafts.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/draft-document-transaction-container/${draftDocumentTransactionContainer.id}`}
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
                        to={`/draft-document-transaction-container/${draftDocumentTransactionContainer.id}/edit`}
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
                          (window.location.href = `/draft-document-transaction-container/${draftDocumentTransactionContainer.id}/delete`)
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
              <Translate contentKey="tfbitaApp.draftDocumentTransactionContainer.home.notFound">
                No Draft Document Transaction Containers found
              </Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default DraftDocumentTransactionContainer;
