import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './category-element.reducer';

export const CategoryElement = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const categoryElementList = useAppSelector(state => state.categoryElement.entities);
  const loading = useAppSelector(state => state.categoryElement.loading);

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
      <h2 id="category-element-heading" data-cy="CategoryElementHeading">
        <Translate contentKey="tfbitaApp.categoryElement.home.title">Category Elements</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="tfbitaApp.categoryElement.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/category-element/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="tfbitaApp.categoryElement.home.createLabel">Create new Category Element</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {categoryElementList && categoryElementList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="tfbitaApp.categoryElement.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('value')}>
                  <Translate contentKey="tfbitaApp.categoryElement.value">Value</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('value')} />
                </th>
                <th className="hand" onClick={sort('categoryName')}>
                  <Translate contentKey="tfbitaApp.categoryElement.categoryName">Category Name</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('categoryName')} />
                </th>
                <th className="hand" onClick={sort('code')}>
                  <Translate contentKey="tfbitaApp.categoryElement.code">Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('code')} />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.categoryElement.orderRegType">Order Reg Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.categoryElement.requestType">Request Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.categoryElement.importType">Import Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.categoryElement.operationTyp">Operation Typ</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.categoryElement.currencyProvisionType">Currency Provision Type</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.categoryElement.paymentTool">Payment Tool</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.categoryElement.activityType">Activity Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.categoryElement.ownerType">Owner Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.categoryElement.status">Status</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.categoryElement.externalCustomerType">External Customer Type</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.categoryElement.transportType">Transport Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.categoryElement.currencySupplier">Currency Supplier</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.categoryElement.statusPurchase">Status Purchase</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.categoryElement.transportVehicleType">Transport Vehicle Type</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.categoryElement.freightLetterType">Freight Letter Type</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.categoryElement.actionCode">Action Code</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.categoryElement.ownershipCode">Ownership Code</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.categoryElement.currencyContainerPlace">Currency Container Place</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.categoryElement.draftSource">Draft Source</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.categoryElement.chargedExchangeBroker">Charged Exchange Broker</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.categoryElement.impartType">Impart Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.categoryElement.insuranceLetterType">Insurance Letter Type</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.categoryElement.advisorDepositType">Advisor Deposit Type</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.categoryElement.interfaceAdvisorDepositType">Interface Advisor Deposit Type</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.categoryElement.paymentType">Payment Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.categoryElement.dealType">Deal Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.categoryElement.coveringAdvisorDepositType">Covering Advisor Deposit Type</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.categoryElement.depositType">Deposit Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.categoryElement.type">Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.categoryElement.secondaryType">Secondary Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.categoryElement.division">Division</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.categoryElement.productDimension">Product Dimension</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.categoryElement.stateOfDocuments">State Of Documents</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.categoryElement.currencyProvisionFileType">Currency Provision File Type</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.categoryElement.statusDraft">Status Draft</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.categoryElement.categoryElement">Category Element</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {categoryElementList.map((categoryElement, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/category-element/${categoryElement.id}`} color="link" size="sm">
                      {categoryElement.id}
                    </Button>
                  </td>
                  <td>{categoryElement.value}</td>
                  <td>{categoryElement.categoryName}</td>
                  <td>{categoryElement.code}</td>
                  <td>
                    {categoryElement.orderRegType ? (
                      <Link to={`/order-registration-info/${categoryElement.orderRegType.id}`}>{categoryElement.orderRegType.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {categoryElement.requestType ? (
                      <Link to={`/order-registration-info/${categoryElement.requestType.id}`}>{categoryElement.requestType.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {categoryElement.importType ? (
                      <Link to={`/order-registration-info/${categoryElement.importType.id}`}>{categoryElement.importType.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {categoryElement.operationTyp ? (
                      <Link to={`/order-registration-info/${categoryElement.operationTyp.id}`}>{categoryElement.operationTyp.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {categoryElement.currencyProvisionType ? (
                      <Link to={`/order-registration-info/${categoryElement.currencyProvisionType.id}`}>
                        {categoryElement.currencyProvisionType.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {categoryElement.paymentTool ? (
                      <Link to={`/order-registration-info/${categoryElement.paymentTool.id}`}>{categoryElement.paymentTool.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {categoryElement.activityType ? (
                      <Link to={`/order-registration-info/${categoryElement.activityType.id}`}>{categoryElement.activityType.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {categoryElement.ownerType ? (
                      <Link to={`/order-registration-info/${categoryElement.ownerType.id}`}>{categoryElement.ownerType.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {categoryElement.status ? (
                      <Link to={`/order-registration-info/${categoryElement.status.id}`}>{categoryElement.status.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {categoryElement.externalCustomerType ? (
                      <Link to={`/order-registration-info/${categoryElement.externalCustomerType.id}`}>
                        {categoryElement.externalCustomerType.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {categoryElement.transportType ? (
                      <Link to={`/order-registration-info/${categoryElement.transportType.id}`}>{categoryElement.transportType.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {categoryElement.currencySupplier ? (
                      <Link to={`/purchase-from-other-resources/${categoryElement.currencySupplier.id}`}>
                        {categoryElement.currencySupplier.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {categoryElement.statusPurchase ? (
                      <Link to={`/purchase-from-other-resources/${categoryElement.statusPurchase.id}`}>
                        {categoryElement.statusPurchase.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {categoryElement.transportVehicleType ? (
                      <Link to={`/order-registration-info/${categoryElement.transportVehicleType.id}`}>
                        {categoryElement.transportVehicleType.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {categoryElement.freightLetterType ? (
                      <Link to={`/draft/${categoryElement.freightLetterType.id}`}>{categoryElement.freightLetterType.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {categoryElement.actionCode ? (
                      <Link to={`/draft/${categoryElement.actionCode.id}`}>{categoryElement.actionCode.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {categoryElement.ownershipCode ? (
                      <Link to={`/draft/${categoryElement.ownershipCode.id}`}>{categoryElement.ownershipCode.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {categoryElement.currencyContainerPlace ? (
                      <Link to={`/draft/${categoryElement.currencyContainerPlace.id}`}>{categoryElement.currencyContainerPlace.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {categoryElement.draftSource ? (
                      <Link to={`/draft/${categoryElement.draftSource.id}`}>{categoryElement.draftSource.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {categoryElement.chargedExchangeBroker ? (
                      <Link to={`/draft/${categoryElement.chargedExchangeBroker.id}`}>{categoryElement.chargedExchangeBroker.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {categoryElement.impartType ? (
                      <Link to={`/draft/${categoryElement.impartType.id}`}>{categoryElement.impartType.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {categoryElement.insuranceLetterType ? (
                      <Link to={`/draft/${categoryElement.insuranceLetterType.id}`}>{categoryElement.insuranceLetterType.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {categoryElement.advisorDepositType ? (
                      <Link to={`/draft/${categoryElement.advisorDepositType.id}`}>{categoryElement.advisorDepositType.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {categoryElement.interfaceAdvisorDepositType ? (
                      <Link to={`/draft/${categoryElement.interfaceAdvisorDepositType.id}`}>
                        {categoryElement.interfaceAdvisorDepositType.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {categoryElement.paymentType ? (
                      <Link to={`/draft/${categoryElement.paymentType.id}`}>{categoryElement.paymentType.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {categoryElement.dealType ? (
                      <Link to={`/draft/${categoryElement.dealType.id}`}>{categoryElement.dealType.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {categoryElement.coveringAdvisorDepositType ? (
                      <Link to={`/draft/${categoryElement.coveringAdvisorDepositType.id}`}>
                        {categoryElement.coveringAdvisorDepositType.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {categoryElement.depositType ? (
                      <Link to={`/advisor-definition/${categoryElement.depositType.id}`}>{categoryElement.depositType.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {categoryElement.type ? <Link to={`/draft-type/${categoryElement.type.id}`}>{categoryElement.type.id}</Link> : ''}
                  </td>
                  <td>
                    {categoryElement.secondaryType ? (
                      <Link to={`/draft-type/${categoryElement.secondaryType.id}`}>{categoryElement.secondaryType.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {categoryElement.division ? (
                      <Link to={`/draft-type/${categoryElement.division.id}`}>{categoryElement.division.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {categoryElement.productDimension ? (
                      <Link to={`/draft-receipt/${categoryElement.productDimension.id}`}>{categoryElement.productDimension.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {categoryElement.stateOfDocuments ? (
                      <Link to={`/draft-receipt/${categoryElement.stateOfDocuments.id}`}>{categoryElement.stateOfDocuments.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {categoryElement.currencyProvisionFileType ? (
                      <Link to={`/draft-receipt/${categoryElement.currencyProvisionFileType.id}`}>
                        {categoryElement.currencyProvisionFileType.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {categoryElement.statusDraft ? (
                      <Link to={`/draft-status-info/${categoryElement.statusDraft.id}`}>{categoryElement.statusDraft.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {categoryElement.categoryElement ? (
                      <Link to={`/category/${categoryElement.categoryElement.id}`}>{categoryElement.categoryElement.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/category-element/${categoryElement.id}`}
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
                        to={`/category-element/${categoryElement.id}/edit`}
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
                        onClick={() => (window.location.href = `/category-element/${categoryElement.id}/delete`)}
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
              <Translate contentKey="tfbitaApp.categoryElement.home.notFound">No Category Elements found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default CategoryElement;
