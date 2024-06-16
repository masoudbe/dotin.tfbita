import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './draft-used-assurance.reducer';

export const DraftUsedAssurance = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const draftUsedAssuranceList = useAppSelector(state => state.draftUsedAssurance.entities);
  const loading = useAppSelector(state => state.draftUsedAssurance.loading);

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
      <h2 id="draft-used-assurance-heading" data-cy="DraftUsedAssuranceHeading">
        <Translate contentKey="tfbitaApp.draftUsedAssurance.home.title">Draft Used Assurances</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="tfbitaApp.draftUsedAssurance.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/draft-used-assurance/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="tfbitaApp.draftUsedAssurance.home.createLabel">Create new Draft Used Assurance</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {draftUsedAssuranceList && draftUsedAssuranceList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="tfbitaApp.draftUsedAssurance.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('assuranceRateId')}>
                  <Translate contentKey="tfbitaApp.draftUsedAssurance.assuranceRateId">Assurance Rate Id</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('assuranceRateId')} />
                </th>
                <th className="hand" onClick={sort('assuranceSerial')}>
                  <Translate contentKey="tfbitaApp.draftUsedAssurance.assuranceSerial">Assurance Serial</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('assuranceSerial')} />
                </th>
                <th className="hand" onClick={sort('exchangeRate')}>
                  <Translate contentKey="tfbitaApp.draftUsedAssurance.exchangeRate">Exchange Rate</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('exchangeRate')} />
                </th>
                <th className="hand" onClick={sort('defaultCurrencyUsedCost')}>
                  <Translate contentKey="tfbitaApp.draftUsedAssurance.defaultCurrencyUsedCost">Default Currency Used Cost</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('defaultCurrencyUsedCost')} />
                </th>
                <th className="hand" onClick={sort('usedCost')}>
                  <Translate contentKey="tfbitaApp.draftUsedAssurance.usedCost">Used Cost</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('usedCost')} />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.draftUsedAssurance.usedAssurances">Used Assurances</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {draftUsedAssuranceList.map((draftUsedAssurance, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/draft-used-assurance/${draftUsedAssurance.id}`} color="link" size="sm">
                      {draftUsedAssurance.id}
                    </Button>
                  </td>
                  <td>{draftUsedAssurance.assuranceRateId}</td>
                  <td>{draftUsedAssurance.assuranceSerial}</td>
                  <td>{draftUsedAssurance.exchangeRate}</td>
                  <td>{draftUsedAssurance.defaultCurrencyUsedCost}</td>
                  <td>{draftUsedAssurance.usedCost}</td>
                  <td>
                    {draftUsedAssurance.usedAssurances ? (
                      <Link to={`/draft/${draftUsedAssurance.usedAssurances.id}`}>{draftUsedAssurance.usedAssurances.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/draft-used-assurance/${draftUsedAssurance.id}`}
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
                        to={`/draft-used-assurance/${draftUsedAssurance.id}/edit`}
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
                        onClick={() => (window.location.href = `/draft-used-assurance/${draftUsedAssurance.id}/delete`)}
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
              <Translate contentKey="tfbitaApp.draftUsedAssurance.home.notFound">No Draft Used Assurances found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default DraftUsedAssurance;
