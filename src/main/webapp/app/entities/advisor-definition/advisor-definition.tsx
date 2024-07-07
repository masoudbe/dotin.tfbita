import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './advisor-definition.reducer';

export const AdvisorDefinition = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const advisorDefinitionList = useAppSelector(state => state.advisorDefinition.entities);
  const loading = useAppSelector(state => state.advisorDefinition.loading);

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
      <h2 id="advisor-definition-heading" data-cy="AdvisorDefinitionHeading">
        <Translate contentKey="tfbitaApp.advisorDefinition.home.title">Advisor Definitions</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="tfbitaApp.advisorDefinition.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/advisor-definition/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="tfbitaApp.advisorDefinition.home.createLabel">Create new Advisor Definition</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {advisorDefinitionList && advisorDefinitionList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="tfbitaApp.advisorDefinition.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('caption')}>
                  <Translate contentKey="tfbitaApp.advisorDefinition.caption">Caption</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('caption')} />
                </th>
                <th className="hand" onClick={sort('code')}>
                  <Translate contentKey="tfbitaApp.advisorDefinition.code">Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('code')} />
                </th>
                <th className="hand" onClick={sort('countryIsoCode')}>
                  <Translate contentKey="tfbitaApp.advisorDefinition.countryIsoCode">Country Iso Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('countryIsoCode')} />
                </th>
                <th className="hand" onClick={sort('depositNum')}>
                  <Translate contentKey="tfbitaApp.advisorDefinition.depositNum">Deposit Num</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('depositNum')} />
                </th>
                <th className="hand" onClick={sort('swiftCode')}>
                  <Translate contentKey="tfbitaApp.advisorDefinition.swiftCode">Swift Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('swiftCode')} />
                </th>
                <th className="hand" onClick={sort('defaultCurrencyCode')}>
                  <Translate contentKey="tfbitaApp.advisorDefinition.defaultCurrencyCode">Default Currency Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('defaultCurrencyCode')} />
                </th>
                <th className="hand" onClick={sort('currenciesCodes')}>
                  <Translate contentKey="tfbitaApp.advisorDefinition.currenciesCodes">Currencies Codes</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('currenciesCodes')} />
                </th>
                <th className="hand" onClick={sort('countryCode')}>
                  <Translate contentKey="tfbitaApp.advisorDefinition.countryCode">Country Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('countryCode')} />
                </th>
                <th className="hand" onClick={sort('bankCode')}>
                  <Translate contentKey="tfbitaApp.advisorDefinition.bankCode">Bank Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('bankCode')} />
                </th>
                <th className="hand" onClick={sort('branchCode')}>
                  <Translate contentKey="tfbitaApp.advisorDefinition.branchCode">Branch Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('branchCode')} />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.advisorDefinition.additionalBrokerInformation">Additional Broker Information</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.advisorDefinition.defaultVostroDeposit">Default Vostro Deposit</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.advisorDefinition.defaultNostroDeposit">Default Nostro Deposit</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.advisorDefinition.receiveMethod">Receive Method</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.advisorDefinition.payMethod">Pay Method</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.advisorDefinition.swiftBic">Swift Bic</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {advisorDefinitionList.map((advisorDefinition, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/advisor-definition/${advisorDefinition.id}`} color="link" size="sm">
                      {advisorDefinition.id}
                    </Button>
                  </td>
                  <td>{advisorDefinition.caption}</td>
                  <td>{advisorDefinition.code}</td>
                  <td>{advisorDefinition.countryIsoCode}</td>
                  <td>{advisorDefinition.depositNum}</td>
                  <td>{advisorDefinition.swiftCode}</td>
                  <td>{advisorDefinition.defaultCurrencyCode}</td>
                  <td>{advisorDefinition.currenciesCodes}</td>
                  <td>{advisorDefinition.countryCode}</td>
                  <td>{advisorDefinition.bankCode}</td>
                  <td>{advisorDefinition.branchCode}</td>
                  <td>
                    {advisorDefinition.additionalBrokerInformation ? (
                      <Link to={`/additional-broker-information/${advisorDefinition.additionalBrokerInformation.id}`}>
                        {advisorDefinition.additionalBrokerInformation.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {advisorDefinition.defaultVostroDeposit ? (
                      <Link to={`/advisor-definition-deposit/${advisorDefinition.defaultVostroDeposit.id}`}>
                        {advisorDefinition.defaultVostroDeposit.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {advisorDefinition.defaultNostroDeposit ? (
                      <Link to={`/advisor-definition-deposit/${advisorDefinition.defaultNostroDeposit.id}`}>
                        {advisorDefinition.defaultNostroDeposit.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {advisorDefinition.receiveMethod ? (
                      <Link to={`/transfer-method-management/${advisorDefinition.receiveMethod.id}`}>
                        {advisorDefinition.receiveMethod.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {advisorDefinition.payMethod ? (
                      <Link to={`/transfer-method-management/${advisorDefinition.payMethod.id}`}>{advisorDefinition.payMethod.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {advisorDefinition.swiftBic ? (
                      <Link to={`/swift-bic/${advisorDefinition.swiftBic.id}`}>{advisorDefinition.swiftBic.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/advisor-definition/${advisorDefinition.id}`}
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
                        to={`/advisor-definition/${advisorDefinition.id}/edit`}
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
                        onClick={() => (window.location.href = `/advisor-definition/${advisorDefinition.id}/delete`)}
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
              <Translate contentKey="tfbitaApp.advisorDefinition.home.notFound">No Advisor Definitions found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default AdvisorDefinition;
