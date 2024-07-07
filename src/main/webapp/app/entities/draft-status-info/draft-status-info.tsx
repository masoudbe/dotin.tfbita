import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './draft-status-info.reducer';

export const DraftStatusInfo = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const draftStatusInfoList = useAppSelector(state => state.draftStatusInfo.entities);
  const loading = useAppSelector(state => state.draftStatusInfo.loading);

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
      <h2 id="draft-status-info-heading" data-cy="DraftStatusInfoHeading">
        <Translate contentKey="tfbitaApp.draftStatusInfo.home.title">Draft Status Infos</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="tfbitaApp.draftStatusInfo.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/draft-status-info/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="tfbitaApp.draftStatusInfo.home.createLabel">Create new Draft Status Info</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {draftStatusInfoList && draftStatusInfoList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="tfbitaApp.draftStatusInfo.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('approved')}>
                  <Translate contentKey="tfbitaApp.draftStatusInfo.approved">Approved</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('approved')} />
                </th>
                <th className="hand" onClick={sort('draftRequestAccepted')}>
                  <Translate contentKey="tfbitaApp.draftStatusInfo.draftRequestAccepted">Draft Request Accepted</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('draftRequestAccepted')} />
                </th>
                <th className="hand" onClick={sort('insuranceCostPaid')}>
                  <Translate contentKey="tfbitaApp.draftStatusInfo.insuranceCostPaid">Insurance Cost Paid</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('insuranceCostPaid')} />
                </th>
                <th className="hand" onClick={sort('issued')}>
                  <Translate contentKey="tfbitaApp.draftStatusInfo.issued">Issued</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('issued')} />
                </th>
                <th className="hand" onClick={sort('otherCostsPaid')}>
                  <Translate contentKey="tfbitaApp.draftStatusInfo.otherCostsPaid">Other Costs Paid</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('otherCostsPaid')} />
                </th>
                <th className="hand" onClick={sort('postSwiftCostPaied')}>
                  <Translate contentKey="tfbitaApp.draftStatusInfo.postSwiftCostPaied">Post Swift Cost Paied</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('postSwiftCostPaied')} />
                </th>
                <th className="hand" onClick={sort('rejectDescription')}>
                  <Translate contentKey="tfbitaApp.draftStatusInfo.rejectDescription">Reject Description</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rejectDescription')} />
                </th>
                <th className="hand" onClick={sort('remainAmount')}>
                  <Translate contentKey="tfbitaApp.draftStatusInfo.remainAmount">Remain Amount</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('remainAmount')} />
                </th>
                <th className="hand" onClick={sort('stampCostPaid')}>
                  <Translate contentKey="tfbitaApp.draftStatusInfo.stampCostPaid">Stamp Cost Paid</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('stampCostPaid')} />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.draftStatusInfo.status">Status</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {draftStatusInfoList.map((draftStatusInfo, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/draft-status-info/${draftStatusInfo.id}`} color="link" size="sm">
                      {draftStatusInfo.id}
                    </Button>
                  </td>
                  <td>{draftStatusInfo.approved ? 'true' : 'false'}</td>
                  <td>{draftStatusInfo.draftRequestAccepted ? 'true' : 'false'}</td>
                  <td>{draftStatusInfo.insuranceCostPaid ? 'true' : 'false'}</td>
                  <td>{draftStatusInfo.issued ? 'true' : 'false'}</td>
                  <td>{draftStatusInfo.otherCostsPaid ? 'true' : 'false'}</td>
                  <td>{draftStatusInfo.postSwiftCostPaied ? 'true' : 'false'}</td>
                  <td>{draftStatusInfo.rejectDescription}</td>
                  <td>{draftStatusInfo.remainAmount}</td>
                  <td>{draftStatusInfo.stampCostPaid ? 'true' : 'false'}</td>
                  <td>
                    {draftStatusInfo.status ? (
                      <Link to={`/category-element/${draftStatusInfo.status.id}`}>{draftStatusInfo.status.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/draft-status-info/${draftStatusInfo.id}`}
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
                        to={`/draft-status-info/${draftStatusInfo.id}/edit`}
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
                        onClick={() => (window.location.href = `/draft-status-info/${draftStatusInfo.id}/delete`)}
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
              <Translate contentKey="tfbitaApp.draftStatusInfo.home.notFound">No Draft Status Infos found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default DraftStatusInfo;
