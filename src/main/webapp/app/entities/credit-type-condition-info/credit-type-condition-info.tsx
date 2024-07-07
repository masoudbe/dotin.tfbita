import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './credit-type-condition-info.reducer';

export const CreditTypeConditionInfo = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const creditTypeConditionInfoList = useAppSelector(state => state.creditTypeConditionInfo.entities);
  const loading = useAppSelector(state => state.creditTypeConditionInfo.loading);

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
      <h2 id="credit-type-condition-info-heading" data-cy="CreditTypeConditionInfoHeading">
        <Translate contentKey="tfbitaApp.creditTypeConditionInfo.home.title">Credit Type Condition Infos</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="tfbitaApp.creditTypeConditionInfo.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/credit-type-condition-info/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="tfbitaApp.creditTypeConditionInfo.home.createLabel">Create new Credit Type Condition Info</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {creditTypeConditionInfoList && creditTypeConditionInfoList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="tfbitaApp.creditTypeConditionInfo.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('durationFrom')}>
                  <Translate contentKey="tfbitaApp.creditTypeConditionInfo.durationFrom">Duration From</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('durationFrom')} />
                </th>
                <th className="hand" onClick={sort('durationTo')}>
                  <Translate contentKey="tfbitaApp.creditTypeConditionInfo.durationTo">Duration To</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('durationTo')} />
                </th>
                <th className="hand" onClick={sort('priceFrom')}>
                  <Translate contentKey="tfbitaApp.creditTypeConditionInfo.priceFrom">Price From</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('priceFrom')} />
                </th>
                <th className="hand" onClick={sort('priceTo')}>
                  <Translate contentKey="tfbitaApp.creditTypeConditionInfo.priceTo">Price To</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('priceTo')} />
                </th>
                <th className="hand" onClick={sort('justificationDisciplinaryTopic')}>
                  <Translate contentKey="tfbitaApp.creditTypeConditionInfo.justificationDisciplinaryTopic">
                    Justification Disciplinary Topic
                  </Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('justificationDisciplinaryTopic')} />
                </th>
                <th className="hand" onClick={sort('openDraftDisciplinaryTopic')}>
                  <Translate contentKey="tfbitaApp.creditTypeConditionInfo.openDraftDisciplinaryTopic">
                    Open Draft Disciplinary Topic
                  </Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('openDraftDisciplinaryTopic')} />
                </th>
                <th className="hand" onClick={sort('otherCostsTopic')}>
                  <Translate contentKey="tfbitaApp.creditTypeConditionInfo.otherCostsTopic">Other Costs Topic</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('otherCostsTopic')} />
                </th>
                <th className="hand" onClick={sort('postTelegraphSwiftCostsTopic')}>
                  <Translate contentKey="tfbitaApp.creditTypeConditionInfo.postTelegraphSwiftCostsTopic">
                    Post Telegraph Swift Costs Topic
                  </Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('postTelegraphSwiftCostsTopic')} />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.creditTypeConditionInfo.defaultCondition">Default Condition</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {creditTypeConditionInfoList.map((creditTypeConditionInfo, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/credit-type-condition-info/${creditTypeConditionInfo.id}`} color="link" size="sm">
                      {creditTypeConditionInfo.id}
                    </Button>
                  </td>
                  <td>{creditTypeConditionInfo.durationFrom}</td>
                  <td>{creditTypeConditionInfo.durationTo}</td>
                  <td>{creditTypeConditionInfo.priceFrom}</td>
                  <td>{creditTypeConditionInfo.priceTo}</td>
                  <td>{creditTypeConditionInfo.justificationDisciplinaryTopic}</td>
                  <td>{creditTypeConditionInfo.openDraftDisciplinaryTopic}</td>
                  <td>{creditTypeConditionInfo.otherCostsTopic}</td>
                  <td>{creditTypeConditionInfo.postTelegraphSwiftCostsTopic}</td>
                  <td>
                    {creditTypeConditionInfo.defaultCondition ? (
                      <Link to={`/credit-type-condition/${creditTypeConditionInfo.defaultCondition.id}`}>
                        {creditTypeConditionInfo.defaultCondition.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/credit-type-condition-info/${creditTypeConditionInfo.id}`}
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
                        to={`/credit-type-condition-info/${creditTypeConditionInfo.id}/edit`}
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
                        onClick={() => (window.location.href = `/credit-type-condition-info/${creditTypeConditionInfo.id}/delete`)}
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
              <Translate contentKey="tfbitaApp.creditTypeConditionInfo.home.notFound">No Credit Type Condition Infos found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default CreditTypeConditionInfo;
