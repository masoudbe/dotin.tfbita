import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './credit-type-condition.reducer';

export const CreditTypeCondition = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const creditTypeConditionList = useAppSelector(state => state.creditTypeCondition.entities);
  const loading = useAppSelector(state => state.creditTypeCondition.loading);

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
      <h2 id="credit-type-condition-heading" data-cy="CreditTypeConditionHeading">
        <Translate contentKey="tfbitaApp.creditTypeCondition.home.title">Credit Type Conditions</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="tfbitaApp.creditTypeCondition.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/credit-type-condition/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="tfbitaApp.creditTypeCondition.home.createLabel">Create new Credit Type Condition</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {creditTypeConditionList && creditTypeConditionList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="tfbitaApp.creditTypeCondition.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('assurancePercentage')}>
                  <Translate contentKey="tfbitaApp.creditTypeCondition.assurancePercentage">Assurance Percentage</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('assurancePercentage')} />
                </th>
                <th className="hand" onClick={sort('commissionRate')}>
                  <Translate contentKey="tfbitaApp.creditTypeCondition.commissionRate">Commission Rate</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('commissionRate')} />
                </th>
                <th className="hand" onClick={sort('customerPrepaymentRateFrom')}>
                  <Translate contentKey="tfbitaApp.creditTypeCondition.customerPrepaymentRateFrom">Customer Prepayment Rate From</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('customerPrepaymentRateFrom')} />
                </th>
                <th className="hand" onClick={sort('customerPrepaymentRateTo')}>
                  <Translate contentKey="tfbitaApp.creditTypeCondition.customerPrepaymentRateTo">Customer Prepayment Rate To</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('customerPrepaymentRateTo')} />
                </th>
                <th className="hand" onClick={sort('durationFrom')}>
                  <Translate contentKey="tfbitaApp.creditTypeCondition.durationFrom">Duration From</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('durationFrom')} />
                </th>
                <th className="hand" onClick={sort('durationTo')}>
                  <Translate contentKey="tfbitaApp.creditTypeCondition.durationTo">Duration To</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('durationTo')} />
                </th>
                <th className="hand" onClick={sort('orderRegistrationRightFrom')}>
                  <Translate contentKey="tfbitaApp.creditTypeCondition.orderRegistrationRightFrom">Order Registration Right From</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('orderRegistrationRightFrom')} />
                </th>
                <th className="hand" onClick={sort('orderRegistrationRightTo')}>
                  <Translate contentKey="tfbitaApp.creditTypeCondition.orderRegistrationRightTo">Order Registration Right To</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('orderRegistrationRightTo')} />
                </th>
                <th className="hand" onClick={sort('postSuspensionPeriodPenaltyRate')}>
                  <Translate contentKey="tfbitaApp.creditTypeCondition.postSuspensionPeriodPenaltyRate">
                    Post Suspension Period Penalty Rate
                  </Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('postSuspensionPeriodPenaltyRate')} />
                </th>
                <th className="hand" onClick={sort('priceFrom')}>
                  <Translate contentKey="tfbitaApp.creditTypeCondition.priceFrom">Price From</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('priceFrom')} />
                </th>
                <th className="hand" onClick={sort('priceTo')}>
                  <Translate contentKey="tfbitaApp.creditTypeCondition.priceTo">Price To</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('priceTo')} />
                </th>
                <th className="hand" onClick={sort('suspensionDurationFrom')}>
                  <Translate contentKey="tfbitaApp.creditTypeCondition.suspensionDurationFrom">Suspension Duration From</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('suspensionDurationFrom')} />
                </th>
                <th className="hand" onClick={sort('suspensionDurationTo')}>
                  <Translate contentKey="tfbitaApp.creditTypeCondition.suspensionDurationTo">Suspension Duration To</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('suspensionDurationTo')} />
                </th>
                <th className="hand" onClick={sort('suspensionPeriodInterestRate')}>
                  <Translate contentKey="tfbitaApp.creditTypeCondition.suspensionPeriodInterestRate">
                    Suspension Period Interest Rate
                  </Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('suspensionPeriodInterestRate')} />
                </th>
                <th className="hand" onClick={sort('updateCommissionRate')}>
                  <Translate contentKey="tfbitaApp.creditTypeCondition.updateCommissionRate">Update Commission Rate</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updateCommissionRate')} />
                </th>
                <th className="hand" onClick={sort('currencyCode')}>
                  <Translate contentKey="tfbitaApp.creditTypeCondition.currencyCode">Currency Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('currencyCode')} />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.creditTypeCondition.serviceOrProduct">Service Or Product</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.creditTypeCondition.neededIdentificationDocTypes">
                    Needed Identification Doc Types
                  </Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.creditTypeCondition.productTypes">Product Types</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.creditTypeCondition.assuranceTypes">Assurance Types</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.creditTypeCondition.creditTypeConditionInfo">Credit Type Condition Info</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {creditTypeConditionList.map((creditTypeCondition, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/credit-type-condition/${creditTypeCondition.id}`} color="link" size="sm">
                      {creditTypeCondition.id}
                    </Button>
                  </td>
                  <td>{creditTypeCondition.assurancePercentage}</td>
                  <td>{creditTypeCondition.commissionRate}</td>
                  <td>{creditTypeCondition.customerPrepaymentRateFrom}</td>
                  <td>{creditTypeCondition.customerPrepaymentRateTo}</td>
                  <td>{creditTypeCondition.durationFrom}</td>
                  <td>{creditTypeCondition.durationTo}</td>
                  <td>{creditTypeCondition.orderRegistrationRightFrom}</td>
                  <td>{creditTypeCondition.orderRegistrationRightTo}</td>
                  <td>{creditTypeCondition.postSuspensionPeriodPenaltyRate}</td>
                  <td>{creditTypeCondition.priceFrom}</td>
                  <td>{creditTypeCondition.priceTo}</td>
                  <td>{creditTypeCondition.suspensionDurationFrom}</td>
                  <td>{creditTypeCondition.suspensionDurationTo}</td>
                  <td>{creditTypeCondition.suspensionPeriodInterestRate}</td>
                  <td>{creditTypeCondition.updateCommissionRate}</td>
                  <td>{creditTypeCondition.currencyCode}</td>
                  <td>
                    {creditTypeCondition.serviceOrProduct ? (
                      <Link to={`/category-element/${creditTypeCondition.serviceOrProduct.id}`}>
                        {creditTypeCondition.serviceOrProduct.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {creditTypeCondition.neededIdentificationDocTypes ? (
                      <Link to={`/objective-category-element/${creditTypeCondition.neededIdentificationDocTypes.id}`}>
                        {creditTypeCondition.neededIdentificationDocTypes.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {creditTypeCondition.productTypes ? (
                      <Link to={`/objective-category-element/${creditTypeCondition.productTypes.id}`}>
                        {creditTypeCondition.productTypes.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {creditTypeCondition.assuranceTypes ? (
                      <Link to={`/objective-category-element/${creditTypeCondition.assuranceTypes.id}`}>
                        {creditTypeCondition.assuranceTypes.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {creditTypeCondition.creditTypeConditionInfo ? (
                      <Link to={`/credit-type-condition-info/${creditTypeCondition.creditTypeConditionInfo.id}`}>
                        {creditTypeCondition.creditTypeConditionInfo.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/credit-type-condition/${creditTypeCondition.id}`}
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
                        to={`/credit-type-condition/${creditTypeCondition.id}/edit`}
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
                        onClick={() => (window.location.href = `/credit-type-condition/${creditTypeCondition.id}/delete`)}
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
              <Translate contentKey="tfbitaApp.creditTypeCondition.home.notFound">No Credit Type Conditions found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default CreditTypeCondition;
