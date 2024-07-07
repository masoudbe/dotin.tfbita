import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './justification-deduction-detail.reducer';

export const JustificationDeductionDetail = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const justificationDeductionDetailList = useAppSelector(state => state.justificationDeductionDetail.entities);
  const loading = useAppSelector(state => state.justificationDeductionDetail.loading);

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
      <h2 id="justification-deduction-detail-heading" data-cy="JustificationDeductionDetailHeading">
        <Translate contentKey="tfbitaApp.justificationDeductionDetail.home.title">Justification Deduction Details</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="tfbitaApp.justificationDeductionDetail.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/justification-deduction-detail/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="tfbitaApp.justificationDeductionDetail.home.createLabel">
              Create new Justification Deduction Detail
            </Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {justificationDeductionDetailList && justificationDeductionDetailList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="tfbitaApp.justificationDeductionDetail.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('deductionAmount')}>
                  <Translate contentKey="tfbitaApp.justificationDeductionDetail.deductionAmount">Deduction Amount</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('deductionAmount')} />
                </th>
                <th className="hand" onClick={sort('equivalentDeductionAmount')}>
                  <Translate contentKey="tfbitaApp.justificationDeductionDetail.equivalentDeductionAmount">
                    Equivalent Deduction Amount
                  </Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('equivalentDeductionAmount')} />
                </th>
                <th className="hand" onClick={sort('receiveCurrencyCode')}>
                  <Translate contentKey="tfbitaApp.justificationDeductionDetail.receiveCurrencyCode">Receive Currency Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('receiveCurrencyCode')} />
                </th>
                <th className="hand" onClick={sort('comment')}>
                  <Translate contentKey="tfbitaApp.justificationDeductionDetail.comment">Comment</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('comment')} />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.justificationDeductionDetail.deductionReason">Deduction Reason</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.justificationDeductionDetail.justificationDeductionAmount">
                    Justification Deduction Amount
                  </Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {justificationDeductionDetailList.map((justificationDeductionDetail, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/justification-deduction-detail/${justificationDeductionDetail.id}`} color="link" size="sm">
                      {justificationDeductionDetail.id}
                    </Button>
                  </td>
                  <td>{justificationDeductionDetail.deductionAmount}</td>
                  <td>{justificationDeductionDetail.equivalentDeductionAmount}</td>
                  <td>{justificationDeductionDetail.receiveCurrencyCode}</td>
                  <td>{justificationDeductionDetail.comment}</td>
                  <td>
                    {justificationDeductionDetail.deductionReason ? (
                      <Link to={`/category-element/${justificationDeductionDetail.deductionReason.id}`}>
                        {justificationDeductionDetail.deductionReason.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {justificationDeductionDetail.justificationDeductionAmount ? (
                      <Link to={`/justification-deduction-amount/${justificationDeductionDetail.justificationDeductionAmount.id}`}>
                        {justificationDeductionDetail.justificationDeductionAmount.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/justification-deduction-detail/${justificationDeductionDetail.id}`}
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
                        to={`/justification-deduction-detail/${justificationDeductionDetail.id}/edit`}
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
                        onClick={() => (window.location.href = `/justification-deduction-detail/${justificationDeductionDetail.id}/delete`)}
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
              <Translate contentKey="tfbitaApp.justificationDeductionDetail.home.notFound">
                No Justification Deduction Details found
              </Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default JustificationDeductionDetail;
