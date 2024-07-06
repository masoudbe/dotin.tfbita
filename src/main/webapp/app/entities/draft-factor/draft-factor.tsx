import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './draft-factor.reducer';

export const DraftFactor = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const draftFactorList = useAppSelector(state => state.draftFactor.entities);
  const loading = useAppSelector(state => state.draftFactor.loading);

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
      <h2 id="draft-factor-heading" data-cy="DraftFactorHeading">
        <Translate contentKey="tfbitaApp.draftFactor.home.title">Draft Factors</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="tfbitaApp.draftFactor.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/draft-factor/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="tfbitaApp.draftFactor.home.createLabel">Create new Draft Factor</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {draftFactorList && draftFactorList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="tfbitaApp.draftFactor.id">ID</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('amount')}>
                  <Translate contentKey="tfbitaApp.draftFactor.amount">Amount</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('amount')} />
                </th>
                <th className="hand" onClick={sort('description')}>
                  <Translate contentKey="tfbitaApp.draftFactor.description">Description</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('description')} />
                </th>
                <th className="hand" onClick={sort('eqAmount')}>
                  <Translate contentKey="tfbitaApp.draftFactor.eqAmount">Eq Amount</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('eqAmount')} />
                </th>
                <th className="hand" onClick={sort('factorDate')}>
                  <Translate contentKey="tfbitaApp.draftFactor.factorDate">Factor Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('factorDate')} />
                </th>
                <th className="hand" onClick={sort('issueDate')}>
                  <Translate contentKey="tfbitaApp.draftFactor.issueDate">Issue Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('issueDate')} />
                </th>
                <th className="hand" onClick={sort('serial')}>
                  <Translate contentKey="tfbitaApp.draftFactor.serial">Serial</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('serial')} />
                </th>
                <th className="hand" onClick={sort('currencyCode')}>
                  <Translate contentKey="tfbitaApp.draftFactor.currencyCode">Currency Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('currencyCode')} />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.draftFactor.draft">Draft</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {draftFactorList.map((draftFactor, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/draft-factor/${draftFactor.id}`} color="link" size="sm">
                      {draftFactor.id}
                    </Button>
                  </td>
                  <td>{draftFactor.amount}</td>
                  <td>{draftFactor.description}</td>
                  <td>{draftFactor.eqAmount}</td>
                  <td>{draftFactor.factorDate}</td>
                  <td>{draftFactor.issueDate}</td>
                  <td>{draftFactor.serial}</td>
                  <td>{draftFactor.currencyCode}</td>
                  <td>{draftFactor.draft ? <Link to={`/draft/${draftFactor.draft.id}`}>{draftFactor.draft.id}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/draft-factor/${draftFactor.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/draft-factor/${draftFactor.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/draft-factor/${draftFactor.id}/delete`)}
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
              <Translate contentKey="tfbitaApp.draftFactor.home.notFound">No Draft Factors found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default DraftFactor;
