import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './purchase-from-other-resources.reducer';

export const PurchaseFromOtherResources = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const purchaseFromOtherResourcesList = useAppSelector(state => state.purchaseFromOtherResources.entities);
  const loading = useAppSelector(state => state.purchaseFromOtherResources.loading);

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
      <h2 id="purchase-from-other-resources-heading" data-cy="PurchaseFromOtherResourcesHeading">
        <Translate contentKey="tfbitaApp.purchaseFromOtherResources.home.title">Purchase From Other Resources</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="tfbitaApp.purchaseFromOtherResources.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/purchase-from-other-resources/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="tfbitaApp.purchaseFromOtherResources.home.createLabel">
              Create new Purchase From Other Resources
            </Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {purchaseFromOtherResourcesList && purchaseFromOtherResourcesList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="tfbitaApp.purchaseFromOtherResources.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('evidenceCode')}>
                  <Translate contentKey="tfbitaApp.purchaseFromOtherResources.evidenceCode">Evidence Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('evidenceCode')} />
                </th>
                <th className="hand" onClick={sort('currencySupplierDescription')}>
                  <Translate contentKey="tfbitaApp.purchaseFromOtherResources.currencySupplierDescription">
                    Currency Supplier Description
                  </Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('currencySupplierDescription')} />
                </th>
                <th className="hand" onClick={sort('amount')}>
                  <Translate contentKey="tfbitaApp.purchaseFromOtherResources.amount">Amount</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('amount')} />
                </th>
                <th className="hand" onClick={sort('purchaseRate')}>
                  <Translate contentKey="tfbitaApp.purchaseFromOtherResources.purchaseRate">Purchase Rate</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('purchaseRate')} />
                </th>
                <th className="hand" onClick={sort('orderRegistrationAmount')}>
                  <Translate contentKey="tfbitaApp.purchaseFromOtherResources.orderRegistrationAmount">Order Registration Amount</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('orderRegistrationAmount')} />
                </th>
                <th className="hand" onClick={sort('requestDate')}>
                  <Translate contentKey="tfbitaApp.purchaseFromOtherResources.requestDate">Request Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('requestDate')} />
                </th>
                <th className="hand" onClick={sort('confirmationDate')}>
                  <Translate contentKey="tfbitaApp.purchaseFromOtherResources.confirmationDate">Confirmation Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('confirmationDate')} />
                </th>
                <th className="hand" onClick={sort('description')}>
                  <Translate contentKey="tfbitaApp.purchaseFromOtherResources.description">Description</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('description')} />
                </th>
                <th className="hand" onClick={sort('purchaseNumber')}>
                  <Translate contentKey="tfbitaApp.purchaseFromOtherResources.purchaseNumber">Purchase Number</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('purchaseNumber')} />
                </th>
                <th className="hand" onClick={sort('purchaseCurrencyName')}>
                  <Translate contentKey="tfbitaApp.purchaseFromOtherResources.purchaseCurrencyName">Purchase Currency Name</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('purchaseCurrencyName')} />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.purchaseFromOtherResources.purchaseFromOtherResources">
                    Purchase From Other Resources
                  </Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {purchaseFromOtherResourcesList.map((purchaseFromOtherResources, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/purchase-from-other-resources/${purchaseFromOtherResources.id}`} color="link" size="sm">
                      {purchaseFromOtherResources.id}
                    </Button>
                  </td>
                  <td>{purchaseFromOtherResources.evidenceCode}</td>
                  <td>{purchaseFromOtherResources.currencySupplierDescription}</td>
                  <td>{purchaseFromOtherResources.amount}</td>
                  <td>{purchaseFromOtherResources.purchaseRate}</td>
                  <td>{purchaseFromOtherResources.orderRegistrationAmount}</td>
                  <td>{purchaseFromOtherResources.requestDate}</td>
                  <td>{purchaseFromOtherResources.confirmationDate}</td>
                  <td>{purchaseFromOtherResources.description}</td>
                  <td>{purchaseFromOtherResources.purchaseNumber}</td>
                  <td>{purchaseFromOtherResources.purchaseCurrencyName}</td>
                  <td>
                    {purchaseFromOtherResources.purchaseFromOtherResources ? (
                      <Link to={`/order-registration-info/${purchaseFromOtherResources.purchaseFromOtherResources.id}`}>
                        {purchaseFromOtherResources.purchaseFromOtherResources.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/purchase-from-other-resources/${purchaseFromOtherResources.id}`}
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
                        to={`/purchase-from-other-resources/${purchaseFromOtherResources.id}/edit`}
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
                        onClick={() => (window.location.href = `/purchase-from-other-resources/${purchaseFromOtherResources.id}/delete`)}
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
              <Translate contentKey="tfbitaApp.purchaseFromOtherResources.home.notFound">No Purchase From Other Resources found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default PurchaseFromOtherResources;
