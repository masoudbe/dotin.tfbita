import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './additional-broker-information.reducer';

export const AdditionalBrokerInformation = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const additionalBrokerInformationList = useAppSelector(state => state.additionalBrokerInformation.entities);
  const loading = useAppSelector(state => state.additionalBrokerInformation.loading);

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
      <h2 id="additional-broker-information-heading" data-cy="AdditionalBrokerInformationHeading">
        <Translate contentKey="tfbitaApp.additionalBrokerInformation.home.title">Additional Broker Informations</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="tfbitaApp.additionalBrokerInformation.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/additional-broker-information/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="tfbitaApp.additionalBrokerInformation.home.createLabel">
              Create new Additional Broker Information
            </Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {additionalBrokerInformationList && additionalBrokerInformationList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="tfbitaApp.additionalBrokerInformation.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('dateOfStartRelation')}>
                  <Translate contentKey="tfbitaApp.additionalBrokerInformation.dateOfStartRelation">Date Of Start Relation</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dateOfStartRelation')} />
                </th>
                <th className="hand" onClick={sort('creditLimit')}>
                  <Translate contentKey="tfbitaApp.additionalBrokerInformation.creditLimit">Credit Limit</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('creditLimit')} />
                </th>
                <th className="hand" onClick={sort('revokedDate')}>
                  <Translate contentKey="tfbitaApp.additionalBrokerInformation.revokedDate">Revoked Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('revokedDate')} />
                </th>
                <th className="hand" onClick={sort('revokedNote')}>
                  <Translate contentKey="tfbitaApp.additionalBrokerInformation.revokedNote">Revoked Note</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('revokedNote')} />
                </th>
                <th className="hand" onClick={sort('confidential')}>
                  <Translate contentKey="tfbitaApp.additionalBrokerInformation.confidential">Confidential</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('confidential')} />
                </th>
                <th className="hand" onClick={sort('otherBrokerServices')}>
                  <Translate contentKey="tfbitaApp.additionalBrokerInformation.otherBrokerServices">Other Broker Services</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('otherBrokerServices')} />
                </th>
                <th className="hand" onClick={sort('sanctionedStatus')}>
                  <Translate contentKey="tfbitaApp.additionalBrokerInformation.sanctionedStatus">Sanctioned Status</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('sanctionedStatus')} />
                </th>
                <th className="hand" onClick={sort('considerations')}>
                  <Translate contentKey="tfbitaApp.additionalBrokerInformation.considerations">Considerations</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('considerations')} />
                </th>
                <th className="hand" onClick={sort('description')}>
                  <Translate contentKey="tfbitaApp.additionalBrokerInformation.description">Description</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('description')} />
                </th>
                <th className="hand" onClick={sort('waysOfCommunication')}>
                  <Translate contentKey="tfbitaApp.additionalBrokerInformation.waysOfCommunication">Ways Of Communication</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('waysOfCommunication')} />
                </th>
                <th className="hand" onClick={sort('servicesAvailable')}>
                  <Translate contentKey="tfbitaApp.additionalBrokerInformation.servicesAvailable">Services Available</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('servicesAvailable')} />
                </th>
                <th className="hand" onClick={sort('customerAcceptancePolicy')}>
                  <Translate contentKey="tfbitaApp.additionalBrokerInformation.customerAcceptancePolicy">
                    Customer Acceptance Policy
                  </Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('customerAcceptancePolicy')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {additionalBrokerInformationList.map((additionalBrokerInformation, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/additional-broker-information/${additionalBrokerInformation.id}`} color="link" size="sm">
                      {additionalBrokerInformation.id}
                    </Button>
                  </td>
                  <td>{additionalBrokerInformation.dateOfStartRelation}</td>
                  <td>{additionalBrokerInformation.creditLimit}</td>
                  <td>{additionalBrokerInformation.revokedDate}</td>
                  <td>{additionalBrokerInformation.revokedNote}</td>
                  <td>{additionalBrokerInformation.confidential}</td>
                  <td>{additionalBrokerInformation.otherBrokerServices}</td>
                  <td>{additionalBrokerInformation.sanctionedStatus}</td>
                  <td>{additionalBrokerInformation.considerations}</td>
                  <td>{additionalBrokerInformation.description}</td>
                  <td>{additionalBrokerInformation.waysOfCommunication}</td>
                  <td>{additionalBrokerInformation.servicesAvailable}</td>
                  <td>{additionalBrokerInformation.customerAcceptancePolicy}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/additional-broker-information/${additionalBrokerInformation.id}`}
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
                        to={`/additional-broker-information/${additionalBrokerInformation.id}/edit`}
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
                        onClick={() => (window.location.href = `/additional-broker-information/${additionalBrokerInformation.id}/delete`)}
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
              <Translate contentKey="tfbitaApp.additionalBrokerInformation.home.notFound">
                No Additional Broker Informations found
              </Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default AdditionalBrokerInformation;
