import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './licence-info.reducer';

export const LicenceInfo = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const licenceInfoList = useAppSelector(state => state.licenceInfo.entities);
  const loading = useAppSelector(state => state.licenceInfo.loading);

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
      <h2 id="licence-info-heading" data-cy="LicenceInfoHeading">
        <Translate contentKey="tfbitaApp.licenceInfo.home.title">Licence Infos</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="tfbitaApp.licenceInfo.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/licence-info/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="tfbitaApp.licenceInfo.home.createLabel">Create new Licence Info</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {licenceInfoList && licenceInfoList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="tfbitaApp.licenceInfo.id">ID</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('organizationLicence')}>
                  <Translate contentKey="tfbitaApp.licenceInfo.organizationLicence">Organization Licence</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('organizationLicence')} />
                </th>
                <th className="hand" onClick={sort('licenceNumber')}>
                  <Translate contentKey="tfbitaApp.licenceInfo.licenceNumber">Licence Number</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('licenceNumber')} />
                </th>
                <th className="hand" onClick={sort('licenceDate')}>
                  <Translate contentKey="tfbitaApp.licenceInfo.licenceDate">Licence Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('licenceDate')} />
                </th>
                <th className="hand" onClick={sort('havingProduct')}>
                  <Translate contentKey="tfbitaApp.licenceInfo.havingProduct">Having Product</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('havingProduct')} />
                </th>
                <th className="hand" onClick={sort('havingService')}>
                  <Translate contentKey="tfbitaApp.licenceInfo.havingService">Having Service</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('havingService')} />
                </th>
                <th className="hand" onClick={sort('creditDate')}>
                  <Translate contentKey="tfbitaApp.licenceInfo.creditDate">Credit Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('creditDate')} />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.licenceInfo.product">Product</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.licenceInfo.service">Service</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.licenceInfo.orderRegistrationInfo">Order Registration Info</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.licenceInfo.orderRegServ">Order Reg Serv</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {licenceInfoList.map((licenceInfo, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/licence-info/${licenceInfo.id}`} color="link" size="sm">
                      {licenceInfo.id}
                    </Button>
                  </td>
                  <td>{licenceInfo.organizationLicence}</td>
                  <td>{licenceInfo.licenceNumber}</td>
                  <td>{licenceInfo.licenceDate}</td>
                  <td>{licenceInfo.havingProduct ? 'true' : 'false'}</td>
                  <td>{licenceInfo.havingService ? 'true' : 'false'}</td>
                  <td>{licenceInfo.creditDate}</td>
                  <td>{licenceInfo.product ? <Link to={`/product/${licenceInfo.product.id}`}>{licenceInfo.product.id}</Link> : ''}</td>
                  <td>
                    {licenceInfo.service ? <Link to={`/service-tariff/${licenceInfo.service.id}`}>{licenceInfo.service.id}</Link> : ''}
                  </td>
                  <td>
                    {licenceInfo.orderRegistrationInfo ? (
                      <Link to={`/order-registration-info/${licenceInfo.orderRegistrationInfo.id}`}>
                        {licenceInfo.orderRegistrationInfo.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {licenceInfo.orderRegServ ? (
                      <Link to={`/order-reg-serv/${licenceInfo.orderRegServ.id}`}>{licenceInfo.orderRegServ.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/licence-info/${licenceInfo.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/licence-info/${licenceInfo.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/licence-info/${licenceInfo.id}/delete`)}
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
              <Translate contentKey="tfbitaApp.licenceInfo.home.notFound">No Licence Infos found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default LicenceInfo;
