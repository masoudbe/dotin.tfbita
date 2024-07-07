import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './advisor-definition-deposit.reducer';

export const AdvisorDefinitionDeposit = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const advisorDefinitionDepositList = useAppSelector(state => state.advisorDefinitionDeposit.entities);
  const loading = useAppSelector(state => state.advisorDefinitionDeposit.loading);

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
      <h2 id="advisor-definition-deposit-heading" data-cy="AdvisorDefinitionDepositHeading">
        <Translate contentKey="tfbitaApp.advisorDefinitionDeposit.home.title">Advisor Definition Deposits</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="tfbitaApp.advisorDefinitionDeposit.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/advisor-definition-deposit/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="tfbitaApp.advisorDefinitionDeposit.home.createLabel">Create new Advisor Definition Deposit</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {advisorDefinitionDepositList && advisorDefinitionDepositList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="tfbitaApp.advisorDefinitionDeposit.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('advisorDepositNumber')}>
                  <Translate contentKey="tfbitaApp.advisorDefinitionDeposit.advisorDepositNumber">Advisor Deposit Number</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('advisorDepositNumber')} />
                </th>
                <th className="hand" onClick={sort('defaultAdvisorDeposit')}>
                  <Translate contentKey="tfbitaApp.advisorDefinitionDeposit.defaultAdvisorDeposit">Default Advisor Deposit</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('defaultAdvisorDeposit')} />
                </th>
                <th className="hand" onClick={sort('depositNum')}>
                  <Translate contentKey="tfbitaApp.advisorDefinitionDeposit.depositNum">Deposit Num</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('depositNum')} />
                </th>
                <th className="hand" onClick={sort('swiftCode')}>
                  <Translate contentKey="tfbitaApp.advisorDefinitionDeposit.swiftCode">Swift Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('swiftCode')} />
                </th>
                <th className="hand" onClick={sort('currencyCode')}>
                  <Translate contentKey="tfbitaApp.advisorDefinitionDeposit.currencyCode">Currency Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('currencyCode')} />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.advisorDefinitionDeposit.depositType">Deposit Type</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.advisorDefinitionDeposit.advisorDefinition">Advisor Definition</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {advisorDefinitionDepositList.map((advisorDefinitionDeposit, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/advisor-definition-deposit/${advisorDefinitionDeposit.id}`} color="link" size="sm">
                      {advisorDefinitionDeposit.id}
                    </Button>
                  </td>
                  <td>{advisorDefinitionDeposit.advisorDepositNumber}</td>
                  <td>{advisorDefinitionDeposit.defaultAdvisorDeposit ? 'true' : 'false'}</td>
                  <td>{advisorDefinitionDeposit.depositNum}</td>
                  <td>{advisorDefinitionDeposit.swiftCode}</td>
                  <td>{advisorDefinitionDeposit.currencyCode}</td>
                  <td>
                    {advisorDefinitionDeposit.depositType ? (
                      <Link to={`/category-element/${advisorDefinitionDeposit.depositType.id}`}>
                        {advisorDefinitionDeposit.depositType.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {advisorDefinitionDeposit.advisorDefinition ? (
                      <Link to={`/advisor-definition/${advisorDefinitionDeposit.advisorDefinition.id}`}>
                        {advisorDefinitionDeposit.advisorDefinition.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/advisor-definition-deposit/${advisorDefinitionDeposit.id}`}
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
                        to={`/advisor-definition-deposit/${advisorDefinitionDeposit.id}/edit`}
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
                        onClick={() => (window.location.href = `/advisor-definition-deposit/${advisorDefinitionDeposit.id}/delete`)}
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
              <Translate contentKey="tfbitaApp.advisorDefinitionDeposit.home.notFound">No Advisor Definition Deposits found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default AdvisorDefinitionDeposit;
