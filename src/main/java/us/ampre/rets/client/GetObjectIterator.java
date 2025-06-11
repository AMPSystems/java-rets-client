package us.ampre.rets.client;

import us.ampre.rets.client.models.SingleObjectResponse;

import java.io.Closeable;
import java.util.Iterator;
/**
 * Iterator for SingleResoponseObjects
 *
 * @param <G>
 */
public interface GetObjectIterator<G extends SingleObjectResponse> extends Closeable, Iterator<G>{
	// noop
}
