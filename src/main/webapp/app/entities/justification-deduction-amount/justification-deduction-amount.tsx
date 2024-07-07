import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './justification-deduction-amount.reducer';

export const JustificationDeductionAmount = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const justificationDeductionAmountList = useAppSelector(state => state.justificationDeductionAmount.entities);
  const loading = useAppSelector(state => state.justificationDeductionAmount.loading);

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
      <h2 id="justification-deduction-amount-heading" data-cy="JustificationDeductionAmountHeading">
        <Translate contentKey="tfbitaApp.justificationDeductionAmount.home.title">Justification Deduction Amounts</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="tfbitaApp.justificationDeductionAmount.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/justification-deduction-amount/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="tfbitaApp.justificationDeductionAmount.home.createLabel">
              Create new Justification Deduction Amount
            </Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {justificationDeductionAmountList && justificationDeductionAmountList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="tfbitaApp.justificationDeductionAmount.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('deductionAmount')}>
                  <Translate contentKey="tfbitaApp.justificationDeductionAmount.deductionAmount">Deduction Amount</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('deductionAmount')} />
                </th>
                <th className="hand" onClick={sort('remainingDeductionAmount')}>
                  <Translate contentKey="tfbitaApp.justificationDeductionAmount.remainingDeductionAmount">
                    Remaining Deduction Amount
                  </Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('remainingDeductionAmount')} />
                </th>
                <th className="hand" onClick={sort('receivedDeductionAmount')}>
                  <Translate contentKey="tfbitaApp.justificationDeductionAmount.receivedDeductionAmount">
                    Received Deduction Amount
                  </Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('receivedDeductionAmount')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {justificationDeductionAmountList.map((justificationDeductionAmount, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/justification-deduction-amount/${justificationDeductionAmount.id}`} color="link" size="sm">
                      {justificationDeductionAmount.id}
                    </Button>
                  </td>
                  <td>{justificationDeductionAmount.deductionAmount}</td>
                  <td>{justificationDeductionAmount.remainingDeductionAmount}</td>
                  <td>{justificationDeductionAmount.receivedDeductionAmount}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/justification-deduction-amount/${justificationDeductionAmount.id}`}
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
                        to={`/justification-deduction-amount/${justificationDeductionAmount.id}/edit`}
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
                        onClick={() => (window.location.href = `/justification-deduction-amount/${justificationDeductionAmount.id}/delete`)}
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
              <Translate contentKey="tfbitaApp.justificationDeductionAmount.home.notFound">
                No Justification Deduction Amounts found
              </Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default JustificationDeductionAmount;
