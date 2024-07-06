import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './swift-bic.reducer';

export const SwiftBic = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const swiftBicList = useAppSelector(state => state.swiftBic.entities);
  const loading = useAppSelector(state => state.swiftBic.loading);

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
      <h2 id="swift-bic-heading" data-cy="SwiftBicHeading">
        <Translate contentKey="tfbitaApp.swiftBic.home.title">Swift Bics</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="tfbitaApp.swiftBic.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/swift-bic/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="tfbitaApp.swiftBic.home.createLabel">Create new Swift Bic</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {swiftBicList && swiftBicList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="tfbitaApp.swiftBic.id">ID</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('address')}>
                  <Translate contentKey="tfbitaApp.swiftBic.address">Address</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('address')} />
                </th>
                <th className="hand" onClick={sort('address2')}>
                  <Translate contentKey="tfbitaApp.swiftBic.address2">Address 2</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('address2')} />
                </th>
                <th className="hand" onClick={sort('address3')}>
                  <Translate contentKey="tfbitaApp.swiftBic.address3">Address 3</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('address3')} />
                </th>
                <th className="hand" onClick={sort('address4')}>
                  <Translate contentKey="tfbitaApp.swiftBic.address4">Address 4</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('address4')} />
                </th>
                <th className="hand" onClick={sort('bank')}>
                  <Translate contentKey="tfbitaApp.swiftBic.bank">Bank</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('bank')} />
                </th>
                <th className="hand" onClick={sort('bankName')}>
                  <Translate contentKey="tfbitaApp.swiftBic.bankName">Bank Name</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('bankName')} />
                </th>
                <th className="hand" onClick={sort('bankName2')}>
                  <Translate contentKey="tfbitaApp.swiftBic.bankName2">Bank Name 2</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('bankName2')} />
                </th>
                <th className="hand" onClick={sort('bankName3')}>
                  <Translate contentKey="tfbitaApp.swiftBic.bankName3">Bank Name 3</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('bankName3')} />
                </th>
                <th className="hand" onClick={sort('branch')}>
                  <Translate contentKey="tfbitaApp.swiftBic.branch">Branch</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('branch')} />
                </th>
                <th className="hand" onClick={sort('branchName')}>
                  <Translate contentKey="tfbitaApp.swiftBic.branchName">Branch Name</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('branchName')} />
                </th>
                <th className="hand" onClick={sort('branchName2')}>
                  <Translate contentKey="tfbitaApp.swiftBic.branchName2">Branch Name 2</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('branchName2')} />
                </th>
                <th className="hand" onClick={sort('city')}>
                  <Translate contentKey="tfbitaApp.swiftBic.city">City</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('city')} />
                </th>
                <th className="hand" onClick={sort('country')}>
                  <Translate contentKey="tfbitaApp.swiftBic.country">Country</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('country')} />
                </th>
                <th className="hand" onClick={sort('location')}>
                  <Translate contentKey="tfbitaApp.swiftBic.location">Location</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('location')} />
                </th>
                <th className="hand" onClick={sort('subTypeIndicator')}>
                  <Translate contentKey="tfbitaApp.swiftBic.subTypeIndicator">Sub Type Indicator</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('subTypeIndicator')} />
                </th>
                <th className="hand" onClick={sort('zip')}>
                  <Translate contentKey="tfbitaApp.swiftBic.zip">Zip</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('zip')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {swiftBicList.map((swiftBic, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/swift-bic/${swiftBic.id}`} color="link" size="sm">
                      {swiftBic.id}
                    </Button>
                  </td>
                  <td>{swiftBic.address}</td>
                  <td>{swiftBic.address2}</td>
                  <td>{swiftBic.address3}</td>
                  <td>{swiftBic.address4}</td>
                  <td>{swiftBic.bank}</td>
                  <td>{swiftBic.bankName}</td>
                  <td>{swiftBic.bankName2}</td>
                  <td>{swiftBic.bankName3}</td>
                  <td>{swiftBic.branch}</td>
                  <td>{swiftBic.branchName}</td>
                  <td>{swiftBic.branchName2}</td>
                  <td>{swiftBic.city}</td>
                  <td>{swiftBic.country}</td>
                  <td>{swiftBic.location}</td>
                  <td>{swiftBic.subTypeIndicator}</td>
                  <td>{swiftBic.zip}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/swift-bic/${swiftBic.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/swift-bic/${swiftBic.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/swift-bic/${swiftBic.id}/delete`)}
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
              <Translate contentKey="tfbitaApp.swiftBic.home.notFound">No Swift Bics found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default SwiftBic;
