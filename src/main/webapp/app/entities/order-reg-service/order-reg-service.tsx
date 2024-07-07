import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './order-reg-service.reducer';

export const OrderRegService = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const orderRegServiceList = useAppSelector(state => state.orderRegService.entities);
  const loading = useAppSelector(state => state.orderRegService.loading);

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
      <h2 id="order-reg-service-heading" data-cy="OrderRegServiceHeading">
        <Translate contentKey="tfbitaApp.orderRegService.home.title">Order Reg Services</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="tfbitaApp.orderRegService.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/order-reg-service/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="tfbitaApp.orderRegService.home.createLabel">Create new Order Reg Service</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {orderRegServiceList && orderRegServiceList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="tfbitaApp.orderRegService.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('amount')}>
                  <Translate contentKey="tfbitaApp.orderRegService.amount">Amount</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('amount')} />
                </th>
                <th className="hand" onClick={sort('currencyAmount')}>
                  <Translate contentKey="tfbitaApp.orderRegService.currencyAmount">Currency Amount</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('currencyAmount')} />
                </th>
                <th className="hand" onClick={sort('unit')}>
                  <Translate contentKey="tfbitaApp.orderRegService.unit">Unit</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('unit')} />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.orderRegService.serviceTariff">Service Tariff</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.orderRegService.orderRegistrationInfo">Order Registration Info</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {orderRegServiceList.map((orderRegService, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/order-reg-service/${orderRegService.id}`} color="link" size="sm">
                      {orderRegService.id}
                    </Button>
                  </td>
                  <td>{orderRegService.amount}</td>
                  <td>{orderRegService.currencyAmount}</td>
                  <td>{orderRegService.unit}</td>
                  <td>
                    {orderRegService.serviceTariff ? (
                      <Link to={`/service-tariff/${orderRegService.serviceTariff.id}`}>{orderRegService.serviceTariff.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {orderRegService.orderRegistrationInfo ? (
                      <Link to={`/order-registration-info/${orderRegService.orderRegistrationInfo.id}`}>
                        {orderRegService.orderRegistrationInfo.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/order-reg-service/${orderRegService.id}`}
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
                        to={`/order-reg-service/${orderRegService.id}/edit`}
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
                        onClick={() => (window.location.href = `/order-reg-service/${orderRegService.id}/delete`)}
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
              <Translate contentKey="tfbitaApp.orderRegService.home.notFound">No Order Reg Services found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default OrderRegService;
